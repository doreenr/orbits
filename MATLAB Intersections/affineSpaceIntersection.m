function intersection = affineSpaceIntersection(varargin)
%AFFINESPACEINTERSECTION    Intersection of affine spaces.
%   intersection = AFFINESPACEINTERSECTION(space1, space2, ...) returns the
%   matrix of row vectors defining the intersection of the given spaces.
%   The spaces must be given as sets of points lying on the space, given as
%   matrices of row vectors.
%   Multiple spaces of different dimension can be passed, as long as all are
%   subspaces of the same space (the number of columns of all the spaces
%   must be equal). This means there are lots of combinations possible:
%   Intersection of lines and lines, lines and planes, planes and planes,
%   ..., all of this in arbitrary dimensions.
%   If there is no intersection, output will be a NaN vector of the spaces'
%   dimension.
%
%   Example 1:
%      % Compute intersection of lines: ((3,0), (3,10)) and ((0,5), (10,5))
%      line1 = [3,0;3,10];
%      line2 = [0,5;10,5];
%      intersection = affineSpaceIntersection(line1, line2)
%
%   Example 2:
%      % Compute intersection of 3D line and plane z=0
%      space1 = rand(2,3); % Line in R^3
%      zZeroPlane = [rand(3,2),zeros(3,1)]; % Plane z=0
%      intersection = affineSpaceIntersection(space1, zZeroPlane)
%
%   Example 3:
%      % Compute intersection of three 2D planes in 5D space.
%      intersection = affineSpaceIntersection(rand(3,5), rand(3,5), rand(3,5))


[nVs,dims] = cellfun(@size,varargin);
assert(all(dims==dims(1)), 'Vectors must be row vectors with equal dimensions (number of columns).');
%% Summation constraints
ends = cumsum(nVs);
starts = [0,ends(1:end-1)]+1;
summationConstraints = bsxfun(@ge,1:ends(end),starts.') & ...
                      bsxfun(@le,1:ends(end),ends.');
%% Add Intersection constraints to summation constraints
intersectionConstraints = [repmat(-varargin{1}.',nargin-1,1), blkdiag(varargin{2:end}).'];
%% Compute Intersection Point
M = [summationConstraints; intersectionConstraints];
s = warning('off','all'); % turn all warnings off
sol = M\[ones(nargin,1);zeros(size(M,1)-nargin,1)];
warning(s);  % restore the warning state
intersection = sol(1:size(varargin{1},1)).'*varargin{1};
%% Check if the intersection is only a single point or an entire space
N = null(M);
if ~isempty(N)
    dirVecs = N(1:size(varargin{1},1),:).'*varargin{1};
    intersection = [intersection; ...
                         bsxfun(@plus,intersection,dirVecs)];
end
end