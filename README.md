# GuessingGame

## Introduction
Guessing Game is an android application that allows user to guess the headline based on the image of the news. 
User can choose to read the full article and highlights before making the decision. 

## The Code
This application is written in kotlin. The archiecture of this app is Model - ViewModel - View (MVVM). In addition, this app
uses dagger 2 for dependency injection, RxJava + Retrofit for internet communication and Room for data persistence.

## The User Interface
This application contains three activities: Splash, Question and Result. <br />
<br />
_Splash_: Only one button: start game. User can click this button to start game. <br />
<br />
_Question_: This page allows user to select the headline that best suits the picture and highlights. The user can click 
"read the article" before make decision. In addition, user can click skip to avoid penalty. <br />
<br />
_Result_: This page shows the result of users' selection, and allows user to jump to next question.

## Contact
For more information, please contact czxbnb@vip.qq.com
