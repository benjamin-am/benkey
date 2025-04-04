# benkey
benkey is a password management tool that provides all of a users passwords in a secure vault. 
It will take in a users one password and give them access to all of their passwords, with the option of random generation, that they can use on any website or app. 
The overall goal of the project is to take all of my passwords off of cloud based platforms, and create my own local platform that I can store myself, on my laptop, desktop, or even USB. 
It plans to give the user complete control of their password management, while only having to remember one password.

## What will the application do?
My project will be a  password management tool, that helps users store all of their passwords locally.

## Who will use it?
Those with millions of passwords (such as myself) who also constantly find themselves forgetting said passwords. This tool is supposed to be a simple implementation of what companies like BitWarden do. 

## Why is this project of interest to you?
I constantly reset my passwords, and I use Google's password manager. I want something that I can control, and to store everything locally as opposed to online.

## Photo Attributions:
Massive props to https://unsplash.com, https://www.stickpng.com and https://www.pexels.com/ for providing free photos 
- [Photo of a Vault](https://unsplash.com/photos/black-and-silver-door-knob-3wPJxh-piRw)
- [Church Hall](https://www.pexels.com/photo/church-hall-with-statue-1711233/)
- [Skull emoji](https://www.stickpng.com/img/icons-logos-emojis/emojis/skull-emoji)
- [Checkmark](https://www.stickpng.com/img/icons-logos-emojis/iconic-brands/century-21-check)


## User Stories
### I called it a password vault here, but you add Accounts to a User, as a user profile is what stores all the user information.
- As a user, I want to be able to create a new Account and add it to a Password Vault
- As a user, I want to be able to select a Password Vault and add a new Password to it
- As a user I want to add multiple accounts to my Password Vault
- As a user I want to list all accounts in my Password Vault
- As a user I want to list all my passwords in my Password Vault
- As a user I want to list all my websites in my Password Vault
- As a user, I want to be able to select a Password Vault, select a website, and see all my account information for it.
- As a user, I want to be able to select a website in my Password Vault, update my usernames, passwords, and user statistics for it all in one go.
- As a user, I want to be able to generate a random password for a specific website and update the password for that website in the software, so I can swiftly update my accounts.
- As a user, I want to be able to copy my password from the software into my clipboard, without having to see or read my password
- As a user, I want to be able to login to the Password Vault with one password, so I only have to remember one password
- As a user, I want to be able to see a dashboard of how many websites I have passwords for, how often I access certain websites passwords, and statistics about sites I never seem to access so I know when to delete accounts
- As a user, I want seamless integration with my browser and my Password Vault, so I have to put in minimal effort when signing in.
- As a user, I want to be able to save my password vault to file (if I so choose). This is done by the main menu.
- As a user, I want to be able to be able to load my password vault from file (if I so choose). This is done by logging in!

## Instructions for End User (GUI)
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by:
  -  creating a new user profile or logging in
  -  going to accounts panel
  -  adding or removing multiple accounts
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
  - viewing the distinct passwords a user has
  - viewing all the websites a user has accounts for with counts
- You can locate my visual component by
  - viewing the intro screen panel
  - viewing the base panel after logging in or signing up
  - after adding an account
  - ater removing an account
- You can save the state of my application by
  - clicking the save button
- You can reload the state of my application by
  - logging in to the program

## Phase 4: Task 2
```
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Accessed user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for ben for website b for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for ben for website b for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for bensnet for website netflix for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for bensnet2 for website netflix for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for bensam for website amazon for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for ben for website pp for user ben
---------------------------------------
Sat Mar 29 11:08:56 PDT 2025
Read account from json for bensAmazon for website amazon for user ben
---------------------------------------
Sat Mar 29 11:09:11 PDT 2025
Added account benEgg for website eggwebsite for user ben
---------------------------------------
Sat Mar 29 11:09:21 PDT 2025
Added account benTest for website testWeb for user ben
---------------------------------------
Sat Mar 29 11:09:27 PDT 2025
Removed account benTest for website testWeb for user ben
---------------------------------------
Sat Mar 29 11:09:35 PDT 2025
Removed account benEgg for website eggwebsite for user ben
---------------------------------------
Sat Mar 29 11:09:47 PDT 2025
Created new user testShow
---------------------------------------
Sat Mar 29 11:10:00 PDT 2025
Added account huhtest for website test for user testShow
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Accessed user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for ben for website b for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for ben for website b for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for bensnet for website netflix for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for bensnet2 for website netflix for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for bensam for website amazon for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for ben for website pp for user ben
---------------------------------------
Sat Mar 29 11:10:07 PDT 2025
Read account from json for bensAmazon for website amazon for user ben
---------------------------------------
Sat Mar 29 11:10:13 PDT 2025
Added account test2 for website test2 for user ben
---------------------------------------
Sat Mar 29 11:10:15 PDT 2025
Saved ben's passwords to vault
```
## Phase 4: Task 3
I would definitely start with a refactor of how I set up my GUI. As can be seen by my UML diagram, I have a decent amount of coupling within my panels. An example of this are the panels that all use card layout, I could have made an abstract card panel that did the similar operations, such as change screen, and kept it all the same. Another example is all the classes that have an instance of main. I could have made a child panel class, that has a parent panel.

Another refactoring I would do is to have more abstraction in my model. I feel like my model is very simple, and I didn't use a ton of object oriented design in it. I could implement more types of applications that you can store accounts for, so instead of just website you can store email accounts, applications, games, etc, which could be accomplished with an abstract class. 

I also want to refactor my persistence library to utilize a database so the application's data isn't stored locally but rather in something like mongoDB or a noSQL equivalent.
