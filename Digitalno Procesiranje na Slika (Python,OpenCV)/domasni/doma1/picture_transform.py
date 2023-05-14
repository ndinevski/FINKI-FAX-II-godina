import cv2
import numpy as np
import matplotlib.pyplot as plt


image = cv2.imread("image1.jpg", cv2.IMREAD_GRAYSCALE)

five_bit = np.divide(image, 8).astype('uint8')
four_bit = np.divide(image, 16).astype('uint8')
three_bit = np.divide(image, 32).astype('uint8')
two_bit = np.divide(image, 64).astype('uint8')
one_bit = np.divide(image, 128).astype('uint8')

fig, axs = plt.subplots(1, 5, figsize=(20, 5))

axs[0].imshow(image, cmap='gray')
axs[0].set_title("Original")
axs[1].imshow(five_bit, cmap='gray')
axs[1].set_title("5 bit")
axs[2].imshow(four_bit, cmap='gray')
axs[2].set_title("4 bit")
axs[3].imshow(three_bit, cmap='gray')
axs[3].set_title("3 bit")
axs[4].imshow(two_bit, cmap='gray')
axs[4].set_title("2 bit")

for ax in axs:
    ax.set_xticks([])
    ax.set_yticks([])
    ax.set_title('')

plt.show()