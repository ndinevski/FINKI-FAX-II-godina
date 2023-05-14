# На почеток ги читаме сликите од датабазата, потоа користејќи otsu сегментација
# добиваме јасно издвоени слики на листови од позадината. Избраниот алгоритам
# точно ги сегментира сликите.

import cv2
import os
import numpy as np

directory = 'database/'

for filename in os.listdir(directory):
    img = cv2.imread(os.path.join(directory, filename))
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    blur = cv2.GaussianBlur(gray, (5, 5), 0)
    ret, th = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)

    cv2.imwrite("segmented_leaves/" + filename, th)




