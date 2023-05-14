import cv2
import numpy as np
import matplotlib.pyplot as plt

img = cv2.imread("monalisa.jpg", cv2.IMREAD_GRAYSCALE)

n = np.array([[1,1,1],[0,0,0],[-1,-1,-1]])
s = np.array([[-1,-1,-1],[0,0,0],[1,1,1]])
w = np.array([[1,0,-1],[1,0,-1],[1,0,-1]])
e = np.array([[-1,0,1],[-1,0,1],[-1,0,1]])
nw = np.array([[1,1,0],[1,0,-1],[0,-1,-1]])
sw = np.array([[0,-1,-1],[1,0,-1],[1,1,0]])
se = np.array([[-1,-1,0],[-1,0,1],[0,1,1]])
ne = np.array([[0,1,1],[-1,0,1],[-1,-1,0]])

# Filters
CompassN = cv2.filter2D(img, -1, n)
CompassS = cv2.filter2D(img, -1, s)
CompassW = cv2.filter2D(img, -1, w)
CompassE = cv2.filter2D(img, -1, e)
CompassNW = cv2.filter2D(img, -1, nw)
CompassSW = cv2.filter2D(img, -1, sw)
CompassSE = cv2.filter2D(img, -1, se)
CompassNE = cv2.filter2D(img, -1, ne)

cv2.namedWindow('All Images', cv2.WINDOW_NORMAL)

img_stack = np.hstack((img, CompassN, CompassS, CompassW, CompassE, CompassNE, CompassSE, CompassNW, CompassSW))

cv2.imshow('All Images', img_stack)
cv2.imshow('Combined Filters', CompassN + CompassS + CompassW + CompassE + CompassNE + CompassSE + CompassNW + CompassSW)
cv2.waitKey(0)
cv2.destroyAllWindows()