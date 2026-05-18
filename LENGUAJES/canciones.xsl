<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Sputify - Artistas</title>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <header>
                    <a href="spotify.xml"><img href="imagenes/header/logo.png"/></a>
                    <a href="spotify.xml"><h1>Sputify</h1></a>
                    <nav>
                        <a href="spotifyArt.xml">Artistas</a>
                        <a href="spotifyAlb.xml">Álbumes</a>
                        <a href="spotifyCan.xml">Canciones</a>
                    </nav>
                </header>
                <main>
                    <xsl:for-each select="//cancion">
                        <h3><xsl:value-of select="nombre"/></h3>
                        <p>Duración:<xsl:value-of select="duracion"/></p>
                        <p>Reproducciones:<xsl:value-of select="NReproducciones"/></p>
                        <!--  Álbum al que pertenece la canción  -->
                        <p>Álbum: </p><a href="spotifyAlb.xml"><xsl:value-of select="../../titulo"/></a>
                        <!--  Artista al que pertenece la canción  -->
                        <p>Artista: </p><a href="spotifyArt.xml"><xsl:value-of select="../../../../nombreArtistico"/></a>
                    </xsl:for-each>
                </main>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>