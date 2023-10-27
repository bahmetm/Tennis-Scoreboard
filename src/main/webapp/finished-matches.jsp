<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/finished-matches.css">
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
            <h1>Finished matches</h1>

            <form action="matches" method="get">
                <label for="player-name-input">Name:</label>
                <input id="player-name-input" name="playerName" type="text" value="${playerName}" required>
                <input type="submit" value="Search">
            </form>

            <table>
                <thead>
                    <tr>
                        <th><p>ID</p></th>
                        <th><p>Player 1</p></th>
                        <th><p>Player 2</p></th>
                        <th><p>Winner</p></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${page.content}" var="match">
                        <tr>
                            <td><p>${match.id}</p></td>
                            <td><p>${match.playerOne.name}</p></td>
                            <td><p>${match.playerTwo.name}</p></td>
                            <td><p>${match.winner.name}</p></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:choose>
                    <c:when test="${empty playerName}">
                        <c:set var="filter" value="" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="filter" value="&filter_by_player_name=${playerName}" />
                    </c:otherwise>
                </c:choose>
                <c:if test="${page.totalPages > 1}">
                    <c:choose>
                        <c:when test="${page.pageNumber == 1}">
                            <div class="page"><a disabled>Prev</a></div>
                        </c:when>
                        <c:when test="${page.pageNumber > 1}">
                            <div class="page"><a href="matches?page=${page.pageNumber - 1}${filter}">Prev</a></div>
                        </c:when>
                    </c:choose>
                    <div class="page"><a disabled>${page.pageNumber}</a></div>
                    <c:choose>
                        <c:when test="${page.pageNumber == page.totalPages}">
                            <div class="page"><a disabled>Next</a></div>
                        </c:when>
                        <c:when test="${page.pageNumber < page.totalPages}">
                            <div class="page"><a href="matches?page=${page.pageNumber + 1}${filter}">Next</a></div>
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
        </main>
    </div>
</div>
</body>
</html>