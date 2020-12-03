# demKare
App to support people with dementia

The app provides features for dementia patients and their caregivers.\
This is a prototype for the app to showcase features and functionality. It's not fully representative of the final product!

## Table of contents
* [How to use](#how-to-use)
* [Installation instructions](#installation-instructions)
* [Code info](#code-info)

## How to use
A patient can interact with a main menu that has lists, information, and assessments.\
A caregiver can manage accounts and interact with their patients' information in menus and lists, and can also evaluate their assessments.\
More information for screens and features follows: 

### Sign in
From this screen the user can sign into the app with their credentials or create an account by pressing the [sign up](#sign-up) button.\
When signing in all the fields are mandatory:
* Username
* Password

For the purposes of this prototype 2 options exist:
* Logging-in as a patient: no fields required to be filled. Leads to [main menu](#main-menu).
* Logging-in as a caregiver: username field must be "caregiver", password field not required to be filled. Leads to [patient list](#patient-list).

Note: For the final app, a database will be present and an account with correct credentials will be required to sign in. 
The distinction as to where the sign in screen takes the user will be made accordingly depending on the user's account type (caregiver or patient) saved in the database.

### Sign up
From this screen the user can create an account by entering information in the fields:
* First name
* Last name
* Username
* Password
* Email
* Birth date
* Telephone

To create an account all the fields are mandatory. The user can select whether they are a caregiver or a patient with a switch.

If all fields are filled the user is taken back to the sign-in screen to [sign in](#sign-in).

### Patient list
From this screen a caregiver can see a list of their patients. Options for this screen include: 
* Calling a patient by pressing the call button next to their name
* [Modifying the patient list](#edit-patient-list) by pressing the edit button on the bottom right
* Going to a patient's [main-menu](#main-menu) screen by pressing their name
* Logging out by pressing the log out button on the top right and entering a password (anything can be entered for this prototype). Returns user to [sign-in screen](#sign-in).

#### Edit Patient list
From this screen a caregiver can manage accounts. The screen shows the usernames for accounts. Options for this screen include:
* Modifying an account by pressing the [edit account](#edit-account) button next to a username
* Deleting an account by pressing the delete button next to a username
* Add an account to the patient list by entering a valid username and pressing add button on the top row.

#### Edit Account
From this screen a caregiver can change information for an account. The fields that can be modified include:
* Email
* Phone
* Username
* Password (needs a verification in following field)
* Password Verification

All fields are mandatory to be filled, otherwise the change can't be saved. Saving changes takes the user back to the [edit patient list](#edit-patient-list) screen.

### Main menu
From this screen the user (whether patient or caregiver) has these options:
* See contact list by pressing [Contacts](#contact-list)
* See people to remember list by pressing [People to Remember](#people-to-remember-list)
* See reminder list by pressing [Reminders](#reminder-list)
* See things to remember list by pressing [Things to Remember](#things-to-remember-list)
* Take an assessment (for patient) or evaluate an assessment (for caregiver) by pressing [Assessment](#assessment)\
(Screen with a switch for these options for this prototype)
* See like list by pressing [What I Like](#like-list)
* See dementia information screen by pressing [Dementia Info](#dementia-info)
* Log out by pressing the log-out button on the top right and entering a password (anything can be entered for this prototype). Returns user to [sign-in screen](#sign-in).

### Contact list
This screen has a patient's contacts. Contacts are presented with a full name (name and surname), relationship with person, phone number, image, and action icons.
A patient or caregiver interacting with the list can:
* [Insert a contact](#insert-contact) by pressing the insert button on the bottom right.
* Call a contact by pressing the call icon next to their image
* [Edit a contact's details](#edit-contact) by pressing the edit icon next to their image
* Edit a contact's image by pressing on the contact's image and choosing an image from gallery
* Delete a contact by pressing the delete icon next to the edit icon

#### Insert contact
From this screen a user can insert a contact by filling the fields:
* Name
* Surname
* Relationship with person
* Phone number

All the fields are mandatory. The user can also insert an image for the contact by pressing the select picture button, otherwise a default purple image will be shown.
Saving leads back to [contact list](#contact-list).

#### Edit contact
From this screen a user can edit a contact's details. The following fields can be modified:
* Name
* Surname
* Relationship with person
* Phone number

All the fields are mandatory. Saving leads back to [contact list](#contact-list).

### People to Remember list
This screen has a patient's people to be remembered. People are presented with a full name (name and surname), relationship with person, image, and action icons.
A patient or caregiver interacting with the list can:
* [Insert a person](#insert-person-to-remember) by pressing the insert button on the bottom right.
* [Edit a person's details](#edit-person-to-remember) by pressing the edit icon next to their image
* Edit a person's image by pressing on the contact's image and choosing an image from gallery
* Delete a person by pressing the delete icon next to the edit icon

#### Insert person to remember
From this screen a user can insert a person by filling the fields:
* Name
* Surname
* Relationship with person

All the fields are mandatory. The user can also insert an image for the person by pressing the select picture button, otherwise a default purple image will be shown.
Saving leads back to [people to remember list](#people-to-remember-list).

#### Edit person to remember
From this screen a user can edit a person's details. The following fields can be modified:
* Name
* Surname
* Relationship with person

All the fields are mandatory. Saving leads back to [people to remember list](#people-to-remember-list).

### Reminder list
This screen has a patient's reminders. Reminders are presented with name, category, date, time, video, and action icons.
A patient or caregiver interacting with the list can:
* [Insert a reminder](#insert-reminder) by pressing the insert button on the bottom right.
* [Edit a reminder's details](#edit-reminder) by pressing the edit icon next to its image
* Delete a reminder by pressing the delete icon next to its image

#### Insert reminder
From this screen a user can insert a reminder by filling the fields:
* Name
* Date (dd//mm/yyyy format)
* Time (hh:mm format)
* Category (select from: General, Today's, Daily)

All the fields are mandatory. The user must also insert a video by pressing the choose video button.
Saving leads back to [reminder list](#reminder-list).

#### Edit reminder
From this screen a user can edit a reminder's details. The following fields can be modified:
* Name
* Date (dd//mm/yyyy format)
* Time (hh:mm format)
* Category (select from: General, Today's, Daily)

All the fields are mandatory. Saving leads back to [reminder list](#reminder-list).

### Things to remember list
This screen has a patient's things to be remembered. Items are presented with a name, image, and action icons. 
A patient or caregiver interacting with the list can:
* [Insert an item](#insert-thing-to-remember) by pressing the insert button on the bottom right.
* [Edit an item's details](#edit-thing-to-remember) by pressing the edit icon next to its image
* Edit an item's image by pressing on the item's image and choosing an image from gallery
* Delete an item by pressing the delete icon next to the edit icon
* View an item's description by pressing the show description button next to its image

#### Insert thing to remember
From this screen a user can insert a item by filling the fields:
* Name
* Description

Only the name field is mandatory. The user can also insert an image for the item by pressing the select picture button, otherwise a default purple image will be shown.
Saving leads back to [things to remember list](#things-to-remember-list).

#### Edit thing to remember
From this screen a user can edit an item's details. The following fields can be modified:
* Name
* Description

Only the name field is mandatory. Saving leads back to [things to remember list](#things-to-remember-list).

### Assessment
From this screen a patient can take an assessment by answering questions and a caregiver can evaluate an assessment with the answers given by the patient.
All fields must be filled, either when marking or evaluating an assessment. 
When evaluating, the score given to an answer that is shown cannot be higher than the max score shown in brackets.

Note: For this prototype, this screen has a switch to alternate between caregiver and patient. The assessment for the prototype is a mock test and is not representative of a real test for the full application!

### Like list
This screen has a patient's likes Items are presented with a name, category, image, and action icons. 
A patient or caregiver interacting with the list can:
* [Insert an item](#insert-liked-item) by pressing the insert button on the bottom right.
* [Edit an item's details](#edit-liked-item) by pressing the edit icon next to its image
* Edit an item's image by pressing on the item's image and choosing an image from gallery
* Delete an item by pressing the delete icon next to the edit icon
* View an item's description by pressing the show description button next to its image

#### Insert liked item
From this screen a user can insert a item by filling the fields:
* Name
* Category (select from: General, Food, Music, Places, Things to do, Sports, Cars)
* Description

All fields except description are mandatory. The user can also insert an image for the item by pressing the select picture button, otherwise a default purple image will be shown.
Saving leads back to [like list](#like-list).

#### Edit liked item
From this screen a user can edit an item's details. The following fields can be modified:
* Name
* Category (select from: General, Food, Music, Places, Things to do, Sports, Cars)
* Description

All fields except description are mandatory. Saving leads back to [like list](#like-list).

### Dementia info
From this screen a user can see information about dementia to learn more about it. A video is also played when pressing the show video button.

Note: The video played when pressing the button is not the final video that will be used for the full application!


## Installation instructions
To install the prototype, download demKare.apk from the repository and run it on an Android device with a version of 4.1.1 or higher.
It's recommended that your device has a 5'6-inch display or higher.

## Code info
This prototype was developed using Android Studio. A database for the app will be developed at a later stage.

To build the project in Android Studio, project files are present in the repository under the demKare folder and can be imported into the tool.\
Notable locations for the project:\
Java classes are saved under: demKare/app/src/main/java/com/example/demKare/\
Layouts are saved under: demKare/app/src/main/res/layout/\
Images and drawables are saved under: demKare/app/src/main/res/drawable/\
Videos are saved under: demKare/app/src/main/res/raw/\
String values are saved in: demKare/app/src/main/res/values/strings.xml

