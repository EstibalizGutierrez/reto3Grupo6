<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Spotify - Inicio</title>
                <link rel="stylesheet" href="styles.css"/>
                <script src="Login.js"></script>
            </head>
            <body>
                <header>
                    <a href="index.html"><img src="imagenes/header/logo.png" alt="logo" /></a>
                    <h2 id="saludo"></h2>
                    <h1>sputify</h1>
                    <a href="login.html">Iniciar sesión</a><!--esto tambien tiene que ser botón-->
                    <button onclick="cerrarSesion()">Cerrar Sesion</button>
                    <nav>
                        <a href="index.html">Inicio</a>
                        <a href="podcasts.html">Artistas</a>
                        <a href="contacto.html">Álbumes</a>
                    </nav>
                </header>

                <main>
                    <h3>Canciones recomendadas</h3>
                    <xsltemplate match="album">
                        <a href="canciones.html"> <img src="imagen" alt=""/></a>
                        <a href="canciones.html"> <img src="hrtbf" alt=""/></a>
                        <a href="canciones.html"> <img src="ifhiofj" alt=""/></a>
                        <a href="canciones.html"> <img src="fef" alt=""/></a>
                    </xsltemplate>
                    <!--nose cuantos poner lol-->

                    <h3>Artistas recomendados</h3>
                    <a href="artistas.html"> <img src="jiod"/> </a>
                    <a href="artistas.html"> <img src="hjtjt"/> </a>
                    <a href="artistas.html"> <img src="rjjrty"/> </a>
                    <a href="artistas.html"> <img src="jyjt"/> </a>
                    <a href="artistas.html"> <img src="jijtod"/> </a>
                    <a href="artistas.html"> <img src="kuyfk"/> </a>
                    <a href="artistas.html"> <img src="jiod"/> </a>
                    <a href="artistas.html"> <img src="jiod"/> </a>
                </main>

                <footer>
                    <img src="imagenes/footer/cc.png" alt="CC"/>
                    <img src="imagenes/footer/x.png" alt="x"/>
                    <img src="imagenes/footer/facebook.png" alt="facebook"/>
                    <img src="imagenes/footer/instagram.png" alt="instagram"/>
                </footer>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>