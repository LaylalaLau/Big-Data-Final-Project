#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 17 14:05:12 2020

@author: liushuxuan
"""

#!/usr/bin/env python


import sys

first = True

for line in sys.stdin:

    if first == True:
        first = False
        print '%s,%s,%s,%s' % ('OFNS', 'TIME', 'YEAR', 'MONTH')
        continue

    # remove leading and trailing whitespace
    line = line.strip()

    flag = False
    line_list = list(line)

    for i in range(len(line_list)):

        if line_list[i] == '"' and flag == False:
            flag = True
        elif line_list[i] == '"' and flag == True:
            flag = False

        if line_list[i] == "," and flag == True:
            line_list[i] = "."

    line = "".join(line_list)

    # split the line into words
    words = line.split(",")

    try:
        OFNS = words[2] + ": " + words[3]
        TIME = words[7][-8:-6]
        YEAR = words[8]
        MONTH = words[9]

        if len(OFNS) != 0 and TIME.isdigit() and YEAR.isdigit() and MONTH.isdigit():
            print '%s,%s,%s,%s' % (OFNS, TIME, YEAR, MONTH)

    except:
        pass





