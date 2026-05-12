<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" doctype-system="about:legacy-compat"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Sputify - Inicio</title>
                <link rel="stylesheet" type="text/css" href="nose.css" />
                <script src="Login.js"></script>
            </head>
            <body> 
                <header>
                    <a href="index.html"><img src="imagenes/header/logo.png" alt="logo" /></a>
                    <h2 id="saludo"></h2>
                    <h1>sputify</h1>
                    <a href="login.html">Iniciar sesión</a><!--esto tambien tiene que ser botón-->
                    <button onclick="cerrarSesion()"></button>
                    <nav>
                        <a href="index.html">Inicio</a>
                        <a href="explorar.html">Explorar</a>
                        <a href="podcasts.html">Podcast</a>
                        <a href="contacto.html">Contacto</a>
                    </nav>
                </header>
                <main>
                    <xsl:choose>
                        
                    </xsl:choose>
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