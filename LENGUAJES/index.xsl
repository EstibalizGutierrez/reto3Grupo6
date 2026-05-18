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
                    <div class="contenedor_header">
                        <a href="spotify.xml"><img class="logo" src="imagenes/header/logo.png" alt="Logo Sputify"/></a>
                        <a href="spotify.xml"><h1 class="titulo">Sputify</h1></a>
                        <button><a href="login.html">Cerrar sesión</a></button>
                    </div>
                    <nav>
                        <a href="spotifyArt.xml">Artistas</a> 
                        <a href="spotifyAlb.xml">Álbumes</a>  
                        <a href="spotifyCan.xml">Canciones</a>
                    </nav>
                </header>
                
                <main>
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
                </main>

                <footer>
                    <div class="contenedor_footer">
                        <img class="creative_commons" src="imagenes/footer/cc.png" alt="Imagen de Creative Commons"/>
                        <div class="texto_contenedor_footer">
                            <p>© 2026 Sputify — Todos los derechos reservados</p>
                        </div>
                        <div class="imagenes_contenedor_footer">
                            <a href="https://www.facebook.com/"><img class="imagenes_redessociales" src="imagenes/footer/facebook.png" alt="Imagen del icono de facebook"/></a>
                            <a href="https://www.instagram.com/"><img class="imagenes_redessociales" src="imagenes/footer/instagram.png" alt="Imagen del icono de instagram"/></a>
                            <a href="https://www.x.com/"><img class="imagenes_redessociales" src="imagenes/footer/x.png" alt="Imagen del icono de twitter"/></a>
                        </div>
                    </div>
                </footer>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>