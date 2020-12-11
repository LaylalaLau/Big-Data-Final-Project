#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 17 14:05:12 2020

@author: liushuxuan
"""

#!/usr/bin/env python

import sys

"""file = open("NYPD_Complaint_Data_Historic.csv", "r")
lines = file.readlines()"""

first = True

for line in sys.stdin:
#for line in lines:

    if first == True:
        first = False
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
        OFNS = words[7]
        #print '%s\t%s' % (OFNS, 1)

        if len(OFNS) != 0:
            #print('%s\t%s' % (OFNS, 1), file=open('output.txt', 'a'))
            print '%s\t%s' % (OFNS, 1)

        else:
            #print('%s\t%s' % ("BAD_RECORD", 1), file=open('output.txt', 'a'))
            print '%s\t%s' % ("BAD_RECORD", 1)

    except:
        print '%s\t%s' % ("BAD_RECORD", 1)
        #sprint('%s\t%s' % ("BAD_RECORD", 1), file=open('output.txt', 'a'))


    
    
    
