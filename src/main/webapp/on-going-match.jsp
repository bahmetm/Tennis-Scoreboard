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
            <a href="@">home</a>
            <a href="@">new match</a>
            <a href="@">matches</a>
        </nav>
    </header>

    <div class="main-wrapper">
        <main>
            <h1>Match score</h1>

            <table>
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
                    <td><p>0</p></td>
                    <td><p>0</p></td>
                    <td><p>0</p></td>
                </tr>

                <tr>
                    <td><p>Federer</p></td>
                    <td><p>0</p></td>
                    <td><p>0</p></td>
                    <td><p>0</p></td>
                </tr>
                </tbody>
            </table>

            <div class="point-buttons-wrapper">
                <form id="wrapper-input-form1" action="/Match-score" method="post">
                    <input type="submit" value="Player 1 wins point" class="input-type">
                </form>

                <form id="wrapper-input-form2" action="/Match-score" method="post">
                    <input type="submit" value="Player 2 wins point"  class="input-type">
                </form>
            </div>
        </main>
    </div>
</body>
</html>