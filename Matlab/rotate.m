for t = linspace(0,10*pi,4000)
    clf;
    R = rot(t,2,5);
    Proj = R(:,[1,3,4]); % x,z,w-axes give an interesting result.
    for i = 1:numPlanes
        Y = reconstructedFaces{i}*Proj;
        patch(Y(:,1), Y(:,2), Y(:,3), C(i,:)); hold on;
        % text(mean(Y(:,1)),mean(Y(:,2)),mean(Y(:,3)),num2str(i));
    end
    cornersTrans = reconstructedVertices*Proj;
    plot3(cornersTrans(:,1),cornersTrans(:,2),cornersTrans(:,3),'.','MarkerSize',20);
    alpha(0.3);
    axis([-5 5 -1 1 -1 1]); axis vis3d; view(3);
    drawnow;
    pause(1/24);
end