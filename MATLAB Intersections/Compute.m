%% %% Requirements:
%%   distancePointsAffineSpace: 
%%       https://gist.github.com/knedlsepp/3e37f147cb4c94ae6223
%%   affineSpaceIntersection:
%%       https://github.com/knedlsepp/affineSpaceIntersection/blob/master/affineSpaceIntersection.m
%%   uniquetol:
%%       http://www.mathworks.com/matlabcentral/fileexchange/27498-unique-with-tolerance/content/uniquetol.m
 
 
%% // Read dataset
%X = dlmread('transformedPCA_10000P3.txt');
%X = dlmread('transP1_10000.txt');

% X = dlmread('transP2_100000.txt'); 
% X = dlmread('transP3_10000.txt'); 
% X = dlmread('transP4_10000.txt'); 
% X = dlmread('transP5_10000.txt'); 
X = dlmread('N3_transP6.txt'); 

%% // Configuration
PointPlaneDistTol = 1e-7;
PointPointDistTol = 1e-7;
PointEdgeDistTol = 0.13;
FaceEdgeDistTol = 0.09;
maxNumPlanes = 1600;
numIterations = 1000;
%% // Match planes to dataset X:
%  // Choose 3 Points randomly. Generate plane. Find points within tol.
pointsWithinTolOf = @(Points,tol,Space) ...
                      distancePointsAffineSpace(Points, Space)<tol;
availablePoints = 1:size(X,1);
[foundPlane, pointsOfPlane] = deal(cell(0));
for i = 1:maxNumPlanes
    disp(['Plane #',num2str(i)]);
    bestNumInliers = 0;
    for j = 1:numIterations
        randomPointsIdxs = availablePoints(randperm(numel(availablePoints),3));
        currentPlane = X(randomPointsIdxs,:);
        inliers = find(pointsWithinTolOf(X, PointPlaneDistTol, currentPlane));
        numInliers = numel(inliers);
        if numInliers > bestNumInliers
            bestCurrentPlane = currentPlane;
            bestInliers = inliers;
            bestNumInliers = numInliers;
        end
    end
    foundPlane{i} = bestCurrentPlane;
    pointsOfPlane{i} = bestInliers;
    availablePoints = setdiff(availablePoints, bestInliers);
    if isempty(availablePoints)
        break;
    end
end
numPlanes = numel(foundPlane);
 
%% // Find all edges for each face
facesAreAdjacent = sparse(numPlanes,numPlanes); % Unused but nice to have.
edges = cell(0);
face2Edges = cell(numPlanes,1);
for comb = nchoosek(1:numPlanes,2).'
    PlanePlaneIntersect = affineSpaceIntersection(foundPlane{comb(1)},foundPlane{comb(2)});
    % In 4D planes might intersect in a single point. We don't want to
    % consider planes neighboring then. Just consider line intersections:
    planeIntersectIsLine = size(PlanePlaneIntersect,1)==2; 
    if planeIntersectIsLine && ~any(isnan(PlanePlaneIntersect(:)))        
        if any(pointsWithinTolOf(X(pointsOfPlane{comb(1)},:), FaceEdgeDistTol, PlanePlaneIntersect)) && ...
           any(pointsWithinTolOf(X(pointsOfPlane{comb(2)},:), FaceEdgeDistTol, PlanePlaneIntersect))
            facesAreAdjacent(comb(1),comb(2)) = true;
            facesAreAdjacent(comb(2),comb(1)) = true;
            edges{end+1} = PlanePlaneIntersect;
            face2Edges{comb(1)} = [face2Edges{comb(1)}; numel(edges)];
            face2Edges{comb(2)} = [face2Edges{comb(2)}; numel(edges)];
        end
    end
end
 
%% // For each face intersect the found edges to get the vertices.
cornerPointsOfFace = cell(numPlanes,1);
for i = 1:numPlanes
    if numel(face2Edges{i})>1
        for comb = nchoosek(face2Edges{i},2).'
            edgeIntersection = affineSpaceIntersection(edges{comb(1)}, edges{comb(2)});
            if size(edgeIntersection,1)==1 % Is point
                if any(pointsWithinTolOf(X(pointsOfPlane{i},:), PointEdgeDistTol, edgeIntersection))
                    cornerPointsOfFace{i} = [cornerPointsOfFace{i}; edgeIntersection];
                end
            end
        end
    end
end

reconstructedVertices = uniquetol(cell2mat(cornerPointsOfFace), PointPointDistTol, 'rows');
 
%% // Reconstruct the face via convex hull of old points + reconstructed vertices
reconstructedFaces = cell(numPlanes,1);
for i = 1:numPlanes
        nodesOfFace = [X(pointsOfPlane{i},:); cornerPointsOfFace{i}];
        [~, localCoords] = projectPointsOntoAffineSpace(nodesOfFace, foundPlane{i});
        hull = convhull(localCoords);
        reconstructedFaces{i} = nodesOfFace(hull,:);
end
 
%% // Animate the reconstructedFaces+cornerPoints
%// Double rotation of x-y, z-w axis:
rot = @(t,a,b) [cos(a*t), -sin(a*t), 0, 0;
                sin(a*t), cos(a*t),  0, 0;
                0, 0, cos(b*t), -sin(b*t);
                0, 0, sin(b*t), cos(b*t);];
C = lines(numPlanes);

gif_fps = 24;
filename = '/Users/Doreen/github/orbits/Matlab INTERSECTIONS/DancP1.gif';
fh = figure(1);

for t = linspace(0,10*pi,4000)
    clf;
    %filename = '/Users/Doreen/github/orbits/Matlab/DancingP1.gif';
    R = rot(t,2,5);
    Proj = R(:,[1,3,4]); % x,z,w-axes give an interesting result.
    for i = 1:numPlanes
        Y = reconstructedFaces{i}*Proj;
        patch(Y(:,1), Y(:,2), Y(:,3), C(i,:),'EdgeColor','none');
        hold on;
        % text(mean(Y(:,1)),mean(Y(:,2)),mean(Y(:,3)),num2str(i));
    end
    cornersTrans = reconstructedVertices*Proj;
    plot3(cornersTrans(:,1),cornersTrans(:,2),cornersTrans(:,3),'.','MarkerSize',20);
    alpha(0.3);
    axis([-5 5 -1 1 -1 1]); 
    axis vis3d; view(3);
    drawnow;
    frame = getframe(gcf,[100 50 400 320]); %(fh);
    im = frame2im(frame);
    [imind,cm] = rgb2ind(im,256);
    if t == 0;
        imwrite(imind,cm, filename,'gif', 'Loopcount',inf);
    else
        imwrite(imind,cm, filename,'gif','WriteMode','append','DelayTime',1/gif_fps);
    end
        
    pause(1/24);
end

%imwrite(im,'DancingP1.gif','DelayTime',0,'LoopCount',inf)