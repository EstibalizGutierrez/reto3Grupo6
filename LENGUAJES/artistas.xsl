<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Artistas - Sputify</title>
                <link rel="stylesheet" href="styles.css"/>
                <script src="Login.js"></script>
            </head>
            <body>
                <header>
                    <h1 id="saludo">Cargando usuario...</h1>
                    <nav>
                        <a href="index.html">Inicio</a>
                        <a href="artistas.html">Artistas</a>
                        <a href="login.html">Salir</a>
                    </nav>
                </header>

                <h2>Nuestros Artistas</h2>

                <div class="contenedor-grid">
                    <xsl:for-each select="//artista">
                        <div class="tarjeta-artista">
                            <img src="{imagen}" alt="{nombreArtistico}" />
                            <h3><xsl:value-of select="nombreArtistico"/></h3>
                            <p><strong>Género:</strong> <xsl:value-of select="genero"/></p>
                            <p class="descripcion"><xsl:value-of select="descripcion"/></p>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>