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
                    <div class="contenedor_header">
                        <a href="spotify.xml"><img class="logo" src="imagenes/header/logo.png" alt="Logo Sputify"/></a>
                        <a href="spotify.xml"><h1 class="titulo">Sputify</h1></a>
                        <button><a href="login.html">Cerrar sesión</a></button>
                    </div>
                    <nav>
                        <a href="spotify.xml">Inicio</a>
                        <a href="spotifyArt.xml">Artistas</a> 
                        <a href="spotifyAlb.xml">Álbumes</a>  
                        <a href="spotifyCan.xml">Canciones</a>
                    </nav>
                </header>
                <main>
                    <h2>Canciones</h2>

                    <div class="contenedor_canciones">
                        <xsl:for-each select="//cancion">
                            <div class="cancion">
                                <h3><xsl:value-of select="nombre"/></h3>
                                <p>Duración: <xsl:value-of select="duracion"/></p>
                                <p>Reproducciones: <xsl:value-of select="NReproducciones"/></p>
                                <!--  Álbum al que pertenece la canción  -->
                                <p>Álbum: <a href="spotifyAlb.xml"><u><xsl:value-of select="../../titulo"/></u></a></p>
                                <!--  Artista al que pertenece la canción  -->
                                <p>Artista: <a href="spotifyArt.xml"><u><xsl:value-of select="../../../../nombreArtistico"/></u></a></p>
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