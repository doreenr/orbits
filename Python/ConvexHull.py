
import matplotlib.pyplot as plt
import numpy as np
from numpy import genfromtxt
from scipy.spatial import ConvexHull




points = genfromtxt('output100.csv', delimiter=',')
print points.shape

# hull = ConvexHull(points, incremental=True, qhull_options = "Qx")
# hullpoints = spatial.ConvexHull(points).points
# hull = Delaunay(points)

from sklearn.decomposition import PCA
model = PCA(n_components=4).fit(points)

proj_points = model.transform(points)
hull_kinda = ConvexHull(proj_points, incremental=True, qhull_options = "Qx")
hull_kinda.simplices
#hull_kinda.vertices

#backpoints = model.inverse_transform(hull_kinda.vertices)

print points
#print backpoints