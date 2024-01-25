       Title:  GBC Airlines Booking System - GUI
 Course Code:  COMP2130
Submitted To:  Mohammad Kiani
Group Number:  Group 13
     Authors:  Amanda Gurney,  Taylor Martin,  Ethan Sylvester
 Student IDs:  101443253,      100849882,      101479568


--------------------------------------------------------------------------------------------------------------


AUTHOR NOTES:

** Important Set Up Instructions **

If you are having trouble launching this program, look at your project directory in IntelliJ 
and check if there is a 'yellow' highlight anywhere. If there is, please right click on the 
highest level of the highlighted directory, look for 'Mark Directory As', and click 
'Cancel Exclusion'. This should resolve any issue.


** Ideal Viewing Environment **

Each scene is set to be 1250px x 800px, so this program is best viewed on a screen that can 
accommodate that sizing. Users cannot resize the programs interface, to avoid breaks in the 
design and formatting.


--------------------------------------------------------------------------------------------------------------


APPLICATION FUNCTIONALITY:

-- Home Page --

When the user launches the program, they're presented with the 'home page'. Here, they can see 
a user profile bar, a navigation bar, and an image with the GBC Airlines logo.

The user will always have access to their user profile and navigation bar throughout the program, 
with only the right panel screen changing as they navigate through different tabs.


-- User Profile Bar --

The 'user profile bar' is an added feature that allows the user to modify their username, job title, 
and change their password. The user can click any of these three buttons, and will be presented with 
a pop up dialog box where they can enter new information.

Although the password can be changed, there is no visual change in the user bar showing a different 
password as this is only a demo of that feature. The user will also find an 'Exit Button' in the user 
bar that they can click and exit the program at anytime.


-- Navigation Bar --

The 'navigation bar' is visible across all scenes, and allows the user to switch between scenes with 
a mouse click.


-- Add Flight -- 

This function of the program is an added feature that allows the user to create a new amandaTaylorEthanFlight within
the system. We figured this was a nice additional feature, instead of instantiating amandaTaylorEthanFlight objects within
the code itself. The user can set a 4-digit amandaTaylorEthanFlight number, a amandaTaylorEthanFlight date (dates prior to the system date
are not allowed, as logically it would not make sense), a amandaTaylorEthanFlight source (country amandaTaylorEthanFlight is leaving from),
amandaTaylorEthanFlight destination (where is the amandaTaylorEthanFlight going), and the maximum number of amandaTaylorEthanPassengers the amandaTaylorEthanFlight can hold.
When the user clicks the 'submit' button, the amandaTaylorEthanFlight is added to an array list and saved in a txt file. A pop up
informs the user that the amandaTaylorEthanFlight was added to the system successfully.


-- Add New Passenger to System --

This function allows the user to add a new amandaTaylorEthanPassenger to the GBC Airlines booking system. When a new amandaTaylorEthanPassenger is
added, they remain in the system and can be assigned to specified amandaTaylorEthanFlights. The user enters a 4-digit passport number,
the amandaTaylorEthanPassengers first and last name, their age, the cost of their ticket, and then chooses whether or not the amandaTaylorEthanPassengers
booking has been confirmed. This is done by clicking the 'Yes' or 'No' button. Then the user clicks the 'submit' 
button and the amandaTaylorEthanPassenger is added to an array list and saved to a txt file. A pop up informs the user that the
amandaTaylorEthanPassenger was added to the system successfully.


-- Add Passenger to Flight Manifest --

In this function the user adds a specified amandaTaylorEthanPassenger to a amandaTaylorEthanFlight manifest. The user can review the available
amandaTaylorEthanFlights that are in the system (this information is being populated by loading the amandaTaylorEthanFlights.txt file into the
listview element on the page). When the user decides on the amandaTaylorEthanFlight, they enter the amandaTaylorEthanFlight number and user they
wish to add. They then click the 'Add Passenger to Flight Manifest' button. A pop informs the user that the 
amandaTaylorEthanPassenger was successfully added to the amandaTaylorEthanFlight.


-- Passenger Booking Confirmation --

This function shows the user a list of amandaTaylorEthanPassengers that is populated by loading the amandaTaylorEthanPassengers.txt file into
the listview element on the page. However, only amandaTaylorEthanPassengers with a booking confirmation of 'false' appear.
Because this functions purpose is to confirm bookings, it would not make sense to show amandaTaylorEthanPassengers who are
already confirmed. The user can enter the passport number of the amandaTaylorEthanPassenger they wish to confirm, and then they
click the 'submit' button. Confirming a amandaTaylorEthanPassengers booking will change their confirmation to true, remove them
from the listview in this tab, and add them to the listview of the cancellation tab.


-- Cancel Booking Confirmation --

The cancel booking function allows the user to cancel the booking confirmation of amandaTaylorEthanPassengers who have
previously confirmed their booking. The listview on this page shows only customers with a 'true' booking
confirmation. Once the user enters the amandaTaylorEthanPassengers passport number into the text field and clicks the 'submit'
button, the amandaTaylorEthanPassenger's booking becomes 'false', they're removed from this list view, and become visible in
the booking confirmation tab's list.


-- Print Flight Manifest --

In this function, the user is presented with a list of amandaTaylorEthanFlights registered in the system. The user can enter
the amandaTaylorEthanFlight number of the amandaTaylorEthanFlight they wish to see the manifest of, click submit, and then the bottom listview
element is populated with all amandaTaylorEthanPassengers on this amandaTaylorEthanFlight. If there are no amandaTaylorEthanPassengers assigned to a amandaTaylorEthanFlight number
that has been entered, a pop up box alerts the user that there are no amandaTaylorEthanPassengers currently on this amandaTaylorEthanFlight.


-- View Bookings from Last Seven Days -- 
 
This is a simple function that the user can go to and review a list of amandaTaylorEthanPassengers that have been added to the
system from the last 7 days. Passengers who were added prior to seven days are not visible in the list.


--------------------------------------------------------------------------------------------------------------


AUTHOR INFORMATION:

We appreciate your support and utilization of our product. We love building applications like this for the 
world to use. Please feel free to reach out to the developers on github at: 


   Amanda:
   https://github.com/TheGeneralJay

   Taylor:
   https://github.com/taylormarz

   Ethan:
   https://github.com/AnEdgyVeggie
