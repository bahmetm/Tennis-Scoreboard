<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/new-match.css">
    <title>Tennis scoreboard</title>
</head>
<body>
<div class="wrapper">
    <header>
        <nav>
            <a href="/">home</a>
            <a href="new-match">new match</a>
            <a href="matches">matches</a>
        </nav>
    </header>

    <div class="main-wrapper">
        <main>
            <h1>New Match</h1>
            <form id="new-match-form" action="new-match" method="POST">

                <div class="text-input-wrapper">
                    <div class="input-wrapper">
                        <label for="player1name">Player 1 name: </label>
                        <input class="${errors.containsKey("playerOneNameNotValid") || errors.containsKey("playerNamesAreSame") ? "invalid-input" : ""}" id="player1name" name="playerOneName" type="text" value="${playerOneName}" required/>
                        <c:if test="${errors.playerOneNameNotValid}">
                            <p class="error-message">${errors.playerOneNameNotValid}</p>
                        </c:if>
                    </div>


                    <div class="input-wrapper">
                        <label for="player2name">Player 2 name: </label>
                        <input class="${errors.containsKey("playerTwoNameNotValid") || errors.containsKey("playerNamesAreSame") ? "invalid-input" : ""}" id="player2name" name="playerTwoName" type="text" value="${playerTwoName}" required/>
                        <c:if test="${errors.playerTwoNameNotValid}">
                            <p class="error-message">${errors.playerTwoNameNotValid}</p>
                        </c:if>
                        <c:if test="${errors.playerNamesAreSame}">
                            <p class="error-message">${errors.playerNamesAreSame}</p>
                        </c:if>
                    </div>
                </div>

                <div class="input-wrapper">
                    <input type="submit" value="Create New Match"/>
                </div>
            </form>
        </main>
    </div>
</div>
</body>
</html>
