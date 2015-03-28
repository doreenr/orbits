function [projectedPoints, coordinates] = projectPointsOntoAffineSpace(Points, Space)
%coordinates are with respect to an orthonormal base located in Space(1,:)
dirVecs = bsxfun(@minus, Space(2:end,:), Space(1,:));
% Orthonormalize the direction vectors;
dirVecs = orth(dirVecs.').';
% Compute coordinates with respect to (Space(1,:),dirVecs)-coordinates
coordinates = bsxfun(@minus, Points, Space(1,:))*dirVecs.';
% Compute projection
projectedPoints = bsxfun(@plus, coordinates*dirVecs, Space(1,:));
end