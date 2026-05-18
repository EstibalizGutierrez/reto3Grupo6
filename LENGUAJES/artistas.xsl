<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Sputify - Artistas</title>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <header>
                    <h1>Sputify</h1>
                    <nav>
                        <a href="spotifyArt.xml">Artistas</a> | 
                        <a href="spotifyAlb.xml">Álbumes</a> | 
                        <a href="spotifyCan.xml">Canciones</a>
                    </nav>
                </header>

                <h2>Nuestros Artistas</h2>
                    <xsl:for-each select="//artista">
                        <img src="{imagen}" alt="{nombreArtistico}"/>
                        <h3><xsl:value-of select="nombreArtistico"/></h3>
                        <p class="genero"><strong>Género:</strong> <xsl:value-of select="genero"/></p>
                        <p><xsl:value-of select="descripcion"/></p>
                    </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>