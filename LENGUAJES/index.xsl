<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <html>
        <body>
        <!--  ══ LOGIN SCREEN ══  -->
        <div id="login-screen">
            <div class="login-box">
                <div class="login-logo">♪ Spotify</div>
                <div class="login-sub">Intranet corporativa · Acceso privado</div>
                <label for="inp-user">Usuario</label>
                <input type="text" id="inp-user" placeholder="Tu nombre de usuario" autocomplete="off"/>
                <label for="inp-pass">Contraseña</label>
                <input type="password" id="inp-pass" placeholder="••••••••••"/>
                <button class="btn-login" onclick="doLogin()">Iniciar sesión</button>
                <div id="login-error"/>
            </div>
        </div>
    <!--  ══ APP ══  -->
        <div id="app">
    <!--  SIDEBAR  -->
        <aside class="sidebar">
            <div class="sidebar-logo">♪
                <span>Intranet</span>
            </div>
            <nav>
                <div class="nav-section">
                    <div class="nav-label">Explorar</div>
                    <button class="nav-btn active" onclick="showPage('artistas')" id="nb-artistas">
                        <span class="icon">🎤</span>
                        <span>Artistas</span>
                    </button>
                    <button class="nav-btn" onclick="showPage('albumes')" id="nb-albumes">
                        <span class="icon">💿</span>
                        <span>Álbumes</span>
                    </button>
                    <button class="nav-btn" onclick="showPage('canciones')" id="nb-canciones">
                        <span class="icon">🎵</span>
                        <span>Canciones</span>
                    </button>
                    <button class="nav-btn" onclick="showPage('podcasts')" id="nb-podcasts">
                        <span class="icon">🎙️</span>
                        <span>Podcasts</span>
                    </button>
                </div>
            </nav>
            <div class="sidebar-footer">
                <div class="user-chip">
                    <div class="user-avatar" id="user-avatar">?</div>
                    <div class="user-info">
                        <div class="user-name" id="user-name-display">—</div>
                        <div class="user-plan" id="user-plan-display">—</div>
                    </div>
                    <button class="btn-logout" onclick="doLogout()" title="Cerrar sesión">X</button>
                </div>
            </div>
        </aside>
    <!--  MAIN  -->
        <main class="main">
            <!--  ── ARTISTAS ──  -->
            <section class="page active" id="page-artistas">
                <div class="page-header">
                    <h1 class="page-title">
                    Artistas
                    <span>ampersand</span>
                    Podcasters
                    </h1>
                    <p class="page-subtitle">Gestión de talento de la plataforma</p>
                </div>
                <div class="filter-bar">
                    <label>Buscar</label>
                    <input type="text" id="f-artista-nombre" placeholder="Nombre artístico…" oninput="filterArtistas()"/>
                    <label>Tipo</label>
                    <select id="f-artista-tipo" onchange="filterArtistas()">
                        <option value="">Todos</option>
                        <option value="musico">Músico</option>
                        <option value="podcaster">Podcaster</option>
                    </select>
                    <label>Género</label>
                    <input type="text" id="f-artista-genero" placeholder="Rap, Pop…" oninput="filterArtistas()"/>
                    <button class="btn-reset" onclick="resetFilter('artistas')">Limpiar</button>
                </div>
                <!--  Estadísticas rápidas  -->
                <div class="stats-row">
                    <div class="stat-card">
                        <div class="stat-val" id="stat-total-artistas">0</div>
                        <div class="stat-label">Artistas totales</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-val" id="stat-total-musicos">0</div>
                        <div class="stat-label">Músicos</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-val" id="stat-total-podcasters">0</div>
                        <div class="stat-label">Podcasters</div>
                    </div>
                </div>
                <div class="grid" id="grid-artistas"/>
            </section>
            <!--  ── ARTISTA DETALLE ──  -->
            <section class="page" id="page-artista-detail">
                <button class="btn-back" onclick="showPage('artistas')">← Volver a Artistas</button>
                <div id="artista-detail-content"/>
            </section>
            <!--  ── ÁLBUMES ──  -->
            <section class="page" id="page-albumes">
                <div class="page-header">
                    <h1 class="page-title">Álbumes</h1>
                    <p class="page-subtitle">Discografía completa de la plataforma</p>
                </div>
                <div class="filter-bar">
                    <label>Buscar</label>
                    <input type="text" id="f-album-titulo" placeholder="Título del álbum…" oninput="filterAlbumes()"/>
                    <label>Artista</label>
                    <input type="text" id="f-album-artista" placeholder="Nombre artístico…" oninput="filterAlbumes()"/>
                    <label>Año</label>
                    <input type="number" id="f-album-anno" placeholder="2010" min="1900" max="2099" oninput="filterAlbumes()"/>
                    <button class="btn-reset" onclick="resetFilter('albumes')">Limpiar</button>
                </div>
                <div class="grid" id="grid-albumes"/>
            </section>
            <!--  ── ÁLBUM DETALLE ──  -->
            <section class="page" id="page-album-detail">
                <button class="btn-back" onclick="showPage('albumes')">← Volver a Álbumes</button>
                <div id="album-detail-content"/>
            </section>
            <!--  ── CANCIONES ──  -->
            <section class="page" id="page-canciones">
                <div class="page-header">
                    <h1 class="page-title">Canciones</h1>
                    <p class="page-subtitle">Catálogo de audio de la plataforma</p>
                </div>
                <div class="filter-bar">
                    <label>Buscar</label>
                    <input type="text" id="f-cancion-nombre" placeholder="Nombre de canción…" oninput="filterCanciones()"/>
                    <label>Artista inv.</label>
                    <input type="text" id="f-cancion-artista" placeholder="Artista…" oninput="filterCanciones()"/>
                    <label>Duración (min ≤)</label>
                    <input type="number" id="f-cancion-dur" placeholder="10" min="1" oninput="filterCanciones()"/>
                    <label>Ordenar por</label>
                    <select id="f-cancion-sort" onchange="filterCanciones()">
                        <option value="">—</option>
                        <option value="reproducciones">Reproducciones</option>
                        <option value="duracion">Duración ↑</option>
                        <option value="nombre">Nombre A-Z</option>
                    </select>
                    <button class="btn-reset" onclick="resetFilter('canciones')">Limpiar</button>
                </div>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th class="td-num">#</th>
                                <th>Título</th>
                                <th>Álbum</th>
                                <th>Artistas inv.</th>
                                <th>Dur.</th>
                                <th>Reproducciones</th>
                            </tr>
                        </thead>
                        <tbody id="tbody-canciones"/>
                    </table>
                </div>
            </section>
            <!--  ── PODCASTS ──  -->
            <section class="page" id="page-podcasts">
                <div class="page-header">
                    <h1 class="page-title">Podcasts</h1>
                    <p class="page-subtitle">Programas y episodios</p>
                </div>
                    <div class="filter-bar">
                        <label>Buscar</label>
                        <input type="text" id="f-podcast-nombre" placeholder="Nombre del episodio…" oninput="filterPodcasts()"/>
                        <label>Podcaster</label>
                        <input type="text" id="f-podcast-caster" placeholder="Nombre del podcaster…" oninput="filterPodcasts()"/>
                        <label>Duración (min ≤)</label>
                        <input type="number" id="f-podcast-dur" placeholder="60" min="1" oninput="filterPodcasts()"/>
                        <button class="btn-reset" onclick="resetFilter('podcasts')">Limpiar</button>
                    </div>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th class="td-num">#</th>
                                <th>Episodio</th>
                                <th>Podcaster</th>
                                <th>Colaboradores</th>
                                <th>Dur.</th>
                                <th>Reproducciones</th>
                            </tr>
                        </thead>
                        <tbody id="tbody-podcasts"/>
                    </table>
                </div>
            </section>
        </main>
    </div>
    </body>
    </html>
</xsl:stylesheet>
