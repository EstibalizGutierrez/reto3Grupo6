<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Sputify - Álbumes</title>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <header>
                    <h1>Sputify</h1>
                    <nav>
                        <a href="spotifyArt.xml">Artistas</a>
                        <a href="spotifyAlb.xml">Álbumes</a> 
                        <a href="spotifyCan.xml">Canciones</a>
                    </nav>
                </header>
                
                <h2>Explorar Álbumes</h2>
                
                <div class="contenedor_albumes">
                    <xsl:for-each select="//album">
                        <div class="albumes">
                            <img src="{imagen}" alt="{titulo}"/>
                            <h3><xsl:value-of select="titulo"/></h3>
                            <p><strong>Lanzamiento:</strong> <xsl:value-of select="anno"/></p>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>