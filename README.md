# Tennis Match Scoreboard Web App

A web-based scoreboard application for tennis matches, built using modern technologies and best practices.

---

## Screenshots

#### New match page
![](https://i.ibb.co/d600gdj/Screenshot-2023-10-27-200136.png)

#### Match score page
![](https://i.ibb.co/R6006b4/Screenshot-2023-10-27-200201.png)

#### Finished matches page
![](https://i.ibb.co/fNPQBc0/Screenshot-2023-10-27-200300.png)

---

## Technologies Used

- **Backend**: Java with Object-Oriented Programming (OOP) principles and collections
- **Architecture**: MVC(S) pattern
- **Build Tool**: Maven
- **Backend Frameworks**:
  - Java Servlets
  - JSP (JavaServer Pages)
- **Web**: Handling GET & POST requests, web forms
- **Database**: SQL using Hibernate ORM, H2 in-memory SQL database
- **Frontend**: HTML/CSS with block-based layout
- **Testing**: Unit testing with JUnit 5
- **Deployment**: Cloud hosting, Linux command line, and Tomcat server

## Motivation

- Create a client-server application with a web interface.
- Gain practical experience with Hibernate ORM.
- Design a simple web interface without relying on third-party libraries.
- Familiarize with the MVC(S) architectural pattern.

## Features

### Match Operations:

- Create a new match
- View and search for finished matches by player names
- Keep track of scores for the current match

### User Interface:

#### Home Page

- Links to the new match page and the list of finished matches.

#### New Match Page

- **URL**: `/new-match`
- **UI**:
  - HTML form with fields for "Player 1 Name" and "Player 2 Name" and a "Start" button.
  - Clicking "Start" initiates a POST request to `/new-match`.
- **POST Handler**:
  - Checks for player existence in the `Players` table. If not found, a new entry is created.
  - Creates an instance of the `Match` class (holding player IDs and current score), stored either in-memory or in key-value storage with UUID as the key.
  - Redirects to `/match-score?uuid=$match_id`.

#### Match Score Page

- **URL**: `/match-score?uuid=$match_id` (`uuid` being the unique identifier for the match)
- **UI**:
  - Displays a table with player names and current score.
  - Provides buttons to indicate which player won the current point.
  - Button presses lead to a POST request to `/match-score?uuid=$match_id`.
- **POST Handler**:
  - Fetches the `Match` instance from storage.
  - Updates the match score based on the player who won the point.
  - Renders the updated score table or the final score if the match ends.

#### Finished Matches Page

- **URL**: `/matches?page=$page_number&filter_by_player_name=$player_name`
- **UI**:
  - A form to filter matches by player name.
  - A list of retrieved matches.
  - Pagination if the number of matches exceeds a single page.

### Database:

**H2 in-memory SQL database** is used, with the following schema:

#### Players Table

| Column Name | Type     | Description                                  |
|-------------|----------|----------------------------------------------|
| ID          | Int      | Primary key, auto-incremented                |
| Name        | Varchar  | Player's name                                |

- Index on the `Name` column for efficient player search.

#### Matches Table

Only finished matches are stored in the database.

| Column Name | Type     | Description                                           |
|-------------|----------|-------------------------------------------------------|
| ID          | Int      | Primary key, auto-incremented                         |
| Player1     | Int      | Player 1's ID, foreign key referencing `Players.ID`    |
| Player2     | Int      | Player 2's ID, foreign key referencing `Players.ID`    |
| Winner      | Int      | Winner's ID, foreign key referencing `Players.ID`      |

---



