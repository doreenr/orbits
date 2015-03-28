
% all = [x1 x2 x3 x4 x5 x6];
% proj = [p1 p2 p3 p4];
proj = [d1 d2 d3 d4];
% c0 = ones(301,1);
% cX1 = [c0 c1 c2 c3 c4 c5 c6];
% cX16 = cX1([1,2,3,4,5,6,7], :);
% cX3D = [c1 c2 c3];

%%% find close points to P0 proj([1],:)
p0 = proj(4,:);
%% point 4 is in some middle

projclose = zeros(9869, 4);
count = 0;
for i = 1:100000
    dist = norm(p0 - proj(i,:));
    if dist < 2
        % indices(i) = i;
        count = count + 1;
        projclose(count, :) = proj(i, :);
    end
end

disp(count)

%%% PLANE based on closepoints around P0
coeffp0 = pca(projclose);
basisp0 = coeffp0(:,1:2);
normalsp0 = coeffp0(:,3:4);
mp0 = mean(projclose);
% 
% test if projclose(i,:) is on plane
figure; 
scatter3(proj(:,1),proj(:,2),proj(:,3), 1); 
hold on;
 
count2 = 1;
plane0 = zeros(2746, 4);

% for i = 1:count
%     test = mp0-(projclose(i,:));
%     dot1 = dot(test, coeffp0(:,3));
%     dot2 = dot(test, coeffp0(:,4));
%     if dot1 < 0.000001 && dot2 < 0.000001
%         plane0(count2, :) = projclose(i, :);
%         scatter3(projclose(i,1),projclose(i,2),projclose(i,3), 10, 'red');
%         count2 = count2 + 1;
%     else
%         scatter3(projclose(i,1),projclose(i,2),projclose(i,3), 10, 'green');
%     end
% end

count2

% scatter3(plane0(:,1),plane0(:,2),plane0(:,3), 10, 'red');
% % hold on;
% % quiver3(mp0(1),mp0(2),mp0(3),normalsp0(1,1),normalsp0(2,1),normalsp0(3,1))
% % quiver3(mp0(1),mp0(2),mp0(3),normalsp0(1,2),normalsp0(2,2),normalsp0(3,2))
% axis equal