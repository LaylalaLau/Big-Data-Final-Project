#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Sep 17 14:11:08 2020

@author: liushuxuan
"""

#!/usr/bin/env python

import sys
from operator import itemgetter


counter = 0

for line in sys.stdin:

    (record, count) = line.strip().split("\t")
        
    try:
        count = int(count)
    except ValueError:
        continue

    counter += count
            
print '%s\t%s' % ('Num of records:', counter)