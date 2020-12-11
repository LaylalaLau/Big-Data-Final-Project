#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 17 14:11:08 2020

@author: liushuxuan
"""

#!/usr/bin/env python

import sys
from operator import itemgetter

"""file = open("output.txt", "r")
lines = file.readlines()"""

current_word = None
current_count = 0
word = None

for line in sys.stdin:
#for line in lines:
    #if line != "\n":
        (word, count) = line.strip().split("\t")
        
        try:
            count = int(count)
        except ValueError:
            continue
        
        if current_word == word:
            current_count += count
        else:
            if current_word:
                print '%s\t%s' % (current_word,current_count)
                #print('%s\t%s' % (current_word,current_count), file=open('final.txt', 'a'))
            (current_word, current_count) = (word, count)
    #else:
        #break
            
if current_word == word:
    print '%s\t%s' % (current_word,current_count)
    #print('%s\t%s' % (current_word, current_count), file=open('final.txt', 'a'))