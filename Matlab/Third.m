

x = d1(1:100);
y = d2(1:100);
p = polyfitn(x,y,3)

if exist('sympoly') == 2
  polyn2sympoly(p)
end
if exist('sym') == 2
  polyn2sym(p)
end

% Evaluate on a grid and plot:
[xg,yg]=meshgrid(-5:0.5:5);
zg = polyvaln(p,[xg(:),yg(:)]);
surf(xg,yg,reshape(zg,size(xg)))
hold on
plot3(x(:,1),x(:,2),y,'o')
hold off


% f = @(x,y,z,t) t.*exp(-x.^2 - y.^2 - z.^2);
% [x,y,z,t] = ndgrid(-1:0.2:1,-1:0.2:1,-1:0.2:1,0:2:10);
% V = f(x,y,z,t);
% [xq,yq,zq,tq] = ndgrid(-1:0.05:1,-1:0.08:1,-1:0.05:1,0:0.5:10);
% Vq = interpn(x,y,z,t,V,xq,yq,zq,tq);
% figure('renderer','zbuffer');
% nframes = size(tq, 4);
% for j = 1:nframes
%    slice(yq(:,:,:,j),xq(:,:,:,j),zq(:,:,:,j),Vq(:,:,:,j),0,0,0);
%    caxis([0 10]);
%    M(j) = getframe;
% end
% movie(M);


% %% Topographic data
% x= [d1(1:1000) d2(1:1000) d3(1:1000)];
% y= d4(1:1000);
% 
% % Two ravines on a hillside. Scanned from a
% % topographic map of an area in upstate New York.
% % plot3(x,y,z,'.')
% 
% d =-5:0.5:5;
% [y0,x0,z0] = ndgrid(d,d,d);
% XI = [x0(:) y0(:) z0(:)];
% YI = griddatan(x,y,XI);
% 
% YI = reshape(YI, size(x0));
% figure
% p = patch(isosurface(x0,y0,z0,YI,10));
% isonormals(x0,y0,z0,YI,p)

