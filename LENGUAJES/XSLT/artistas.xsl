<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" doctype-system="about:legacy-compat"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Artistas</title>
                <script src="Login.js"></script>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <header>
                    <div id="saludo"></div> 
                </header>
                <h2>Nuestros Artistas</h2>
                
                <xsl:for-each select="spotify/artistas/artista">
                        <img src="{imagen}" alt="{nombreArtistico}" />
                        <h3><xsl:value-of select="nombreArtistico"/></h3>
                        <p><strong>Género:</strong> <xsl:value-of select="genero"/></p>
                        <p style="font-size: 0.9em;"><xsl:value-of select="descripcion"/></p>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>