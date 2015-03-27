
%%% find close points to P0 on plane0
p0 = plane0(1,:);

projclose = zeros(10000, 4);
count = 0;
for i = 1:12436
    dist = norm(p0 - plane0(i,:));
    if dist < 8
        count = count + 1;
        projclose(count, :) = plane0(i, :);
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
scatter3(plane0(:,1),plane0(:,2),plane0(:,3), 1);
hold on;
% 
% indices0 = [];
% 
count2 = 1;
plane1 = zeros(2746, 4);

for i = 1:count
    test = mp0-(projclose(i,:));
    dot1 = dot(test, coeffp0(:,3));
    dot2 = dot(test, coeffp0(:,4));
    if dot1 < 0.000001 && dot2 < 0.000001
        plane1(count2, :) = projclose(i, :);
        scatter3(projclose(i,1),projclose(i,2),projclose(i,3), 10, 'red');
        count2 = count2 + 1;
    else
        scatter3(projclose(i,1),projclose(i,2),projclose(i,3), 10, 'green');
    end
end

count2

% scatter3(plane1(:,1),plane1(:,2),plane1(:,3), 10, 'red');
% % hold on;
% % quiver3(mp0(1),mp0(2),mp0(3),normalsp0(1,1),normalsp0(2,1),normalsp0(3,1))
% % quiver3(mp0(1),mp0(2),mp0(3),normalsp0(1,2),normalsp0(2,2),normalsp0(3,2))
% axis equal