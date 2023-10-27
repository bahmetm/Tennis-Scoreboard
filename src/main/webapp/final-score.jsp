<%--
  Created by IntelliJ IDEA.
  User: quant
  Date: 10/26/2023
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/on-going-match.css">

    <title>Match score</title>
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
            <h1>Match finished!</h1>
            <h3>Player "${match.winner.name}" wins!</h3>

            <table class="match-score-table">
                <thead>
                <tr>
                    <th class="player"><p>PLAYER</p></th>
                    <th class="set"><p>SETS</p></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><p>${match.playerOne.name}</p></td>
                    <td><p>${match.matchScore.sets[0]}</p></td>
                </tr>
                <tr>
                    <td><p>${match.playerTwo.name}</p></td>
                    <td><p>${match.matchScore.sets[1]}</p></td>
                </tr>
                </tbody>
            </table>
        </main>
    </div>
</body>
</html>