<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Match score</h1>

            <table class="match-score-table">
                <thead>
                    <tr>
                        <th><p>PLAYER</p></th>
                        <th><p>SET</p></th>
                        <th><p>GAME</p></th>
                        <th><p>POINT</p></th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td><p>${match.playerOne.name}</p></td>
                        <td><p>${match.matchScore.sets[0]}</p></td>
                        <td><p>${match.matchScore.games[0]}</p></td>
                        <c:choose>
                            <c:when test="${match.matchScore.ads[0] == 0 && match.matchScore.ads[1] == 0}">
                                <td><p>${match.matchScore.points[0]}</p></td>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${match.matchScore.ads[0] == 1 && match.matchScore.ads[1] == 0}">
                                        <td><p>AD</p></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><p></p></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </tr>

                    <tr>
                        <td><p>${match.playerTwo.name}</p></td>
                        <td><p>${match.matchScore.sets[1]}</p></td>
                        <td><p>${match.matchScore.games[1]}</p></td>
                        <c:choose>
                            <c:when test="${match.matchScore.ads[0] == 0 && match.matchScore.ads[1] == 0}">
                                <td><p>${match.matchScore.points[1]}</p></td>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${match.matchScore.ads[0] == 0 && match.matchScore.ads[1] == 1}">
                                        <td><p>AD</p></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><p></p></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </tbody>
            </table>

            <c:if test="${isTieBreak}">
                <h3>Tie-break!</h3>
                <table class="tie-break-table">
                    <thead>
                    <tr>
                        <td><p>${match.playerOne.name}</p></td>
                        <td><p>${match.playerTwo.name}</p></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><p>${match.matchScore.tieBreakPoints[0]}</p></td>
                        <td><p>${match.matchScore.tieBreakPoints[1]}</p></td>
                    </tr>
                    </tbody>
                </table>
            </c:if>


            <form id="wrapper-input-form" action="match-score?uuid=${uuid}" method="post">
                <button class="button" name="playerNumberParameter" value="0">Player 1 wins point</button>
                <button class="button" name="playerNumberParameter" value="1">Player 2 wins point</button>
            </form>
        </main>
    </div>
</body>
</html>