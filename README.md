# SoccerPlayers
 
In this application I decided to present a list of football players of the FC Barcelona team.

In the first activity I displayed with the help of a RecyclerView a list made up of a simple header,
and several objects represented on a CardView with the following data: image, name, team and nationality.

In the second activity I display the actor's name along with the image, age, team and a button that will take me to the next activity.

In the third activity, all the data about the current player are displayed together with a button that will take me to activity 4.

In the fourth activity, a page with player data will open in a WebView.
Here we have implemented a menu with two functionalities: the first being the search for a word,
entered from the keyboard on that page, and the second by creating a history when navigating to another address. From here we are sent to the last activity.

In activity 5 we created an address list that represents the online history of WebView.
This list is represented by a RecyclerView with a custom adapter. When selecting an address we are sent back to activity 4.

For the design contribution part, certain shapes have been created for Buttons and TextViews, together with an icon for the application.
For the technical contribution part, the menu with the two functionalities was created, addressed to WebView.

All data was retrieved from XML and distributed to activities through the Bundle.

The application was designed and created for the Pixel3a API28.
