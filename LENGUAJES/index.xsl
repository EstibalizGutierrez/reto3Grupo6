<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Sputify - Inicio</title>
                <link rel="stylesheet" href="styles.css"/>
            </head>

            <body>
                <header>
                    <a href="spotify.xml"><img href="imagenes/header/logo.png"/></a>
                    <h1>Sputify</h1>
                    <nav>
                        <a href="spotifyArt.xml" class="enlace">Artistas</a> 
                        <a href="spotifyAlb.xml" class="enlace">Álbumes</a>  
                        <a href="spotifyCan.xml" class="enlace">Canciones</a>
                    </nav>
                </header>
                
                <div class="contenedor_artistas">
                    <xsl:for-each select="//artista">
                        <div class="artista">
                            <a href="spotifyArt.xml"><img src="{imagen}" alt="{nombreArtistico}"/></a>
                            <a href="spotifyArt.xml"><h2><xsl:value-of select="nombreArtistico"/></h2></a>

                            <div class="info_tipo">
                                <xsl:if test="musico">
                                    <xsl:value-of select="musico/@caracteristica"/>
                                </xsl:if>
                                <xsl:if test="podcaster">
                                    Podcaster
                                </xsl:if>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>

                <div class="contenedor_albumes">
                    <xsl:for-each select="//album">
                        <div class="album">
                            <a href="spotifyAlb.xml"><img src="{imagen}" alt="{titulo}"/></a>
                            <a href="spotifyAlb.xml"><h2><xsl:value-of select="titulo"/></h2></a>
                            <a href="artistas.xsl"><p><xsl:value-of select="../../nombreArtistico"/></p></a>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>