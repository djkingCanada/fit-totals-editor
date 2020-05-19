# Fit Totals Editor
Edit Values in fit format, tested on Garmin 520 Edge

Garming 520 stores totals in /Totals/Totals.fit

Copy the Totals.fit file to the CWD.

Run the code once with no arguments to see the totals displayed on the console.

Invoke with arguments index of the total you want to edit and the number of units you want to add.

The device total (sum of the other totals) is stored at index zero and the values added will be added to the device total as well.

Edited file is placed in edit/Totals.fit

Copy the file to (your gamin device)/Garmin/NewFiles/Totals.fit

Unmount the device, and when it starts your new values should be in totals.

This code requires the fit sdk from This is Ant
https://www.thisisant.com/developer/resources/downloads/
