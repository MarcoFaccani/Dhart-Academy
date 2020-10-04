class Header extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <nav class="navbar navbar-expand-lg">
                <div class="container">
                    <!-- Logo and brand name -->
                    <span class="navbar-brand">
                      <a href="/" class="navbar-brand">
                          <img
                            th:attr="srcset=|
                                @{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_256/v1601743214/dhart-academy/logo-resized_kcg9tz.png'} 256w,
                                @{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_512/v1601743214/dhart-academy/logo-resized_kcg9tz.png'} 512w,
                                @{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_768/v1601743214/dhart-academy/logo-resized_kcg9tz.png'} 768w,
                                @{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_1024/v1601743214/dhart-academy/logo-resized_kcg9tz.png'} 1024w,
                                @{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_1280/v1601743214/dhart-academy/logo-resized_kcg9tz.png'} 1280w|"
                            th:src="@{${cloudinaryBaseUrl} + ${cloudinaryTransfCommon} + 'w_auto' + '/v1601743214/dhart-academy/logo-resized_kcg9tz.png'}" class="img-fluid rounded-circle" width="70" alt="logo"
                          />
                          Dhart Academy
                      </a>
                    </span>
        
                    <!-- toggler -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
        
                    <!-- navbar content -->
                    <div id="navbarContent" class="collapse navbar-collapse">
        
                        <ul class="navbar-nav mx-auto">
                            <li class="nav-item active">
                                <a href="/" class="nav-link">Home<span class="sr-only">(current)</span></a>
                            </li>
        
                            <!-- corsi dropdown -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbar-courses-dropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Corsi
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbar-courses-dropdown">
                                    <a class="dropdown-item" href="/corsi/breakdance">Breakdance</a>
                                    <a class="dropdown-item" href="/corsi/hiphop">Hip Hop</a>
                                    <a class="dropdown-item" href="/corsi/house">House</a>
                                    <a class="dropdown-item" href="/corsi/dancehall">Dancehall</a>
                                    <a class="dropdown-item" href="/corsi/vouge">Vouge</a>
                                    <a class="dropdown-item" href="/corsi/floorwork">Floorwork</a>
                                    <a class="dropdown-item" href="/corsi/dj">DJ</a>
                                    <a class="dropdown-item" href="/corsi/graffiti">Graffiti</a>
                                </div>
                            </li>
        
                            <li class="nav-item"><a href="#" class="nav-link">News</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">Octopus Mind</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">Groovz</a></li>
                            <li class="nav-item"><a href="#" class="nav-link">Blog</a></li>
                        </ul>
        
                        <!-- navbar contatti -->
                        <ul class="list-inline ml-auto mb-0">
                            <li class="list-inline-item">
                                <a href="/contatti" class="btn btn-gradient-blue px-5 py-2 my-2">Contatti</a>
                            </li>
                            <!-- social media icons -->
                            <div class="navbar-icone-social-media d-flex justify-content-center">
                                <li class="list-inline-item p-1">
                                    <a href="https://www.facebook.com/DhartAcademy">
                                        <i class="fab fa-facebook fa-lg"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item p-1">
                                    <a href="https://www.instagram.com/dhartacademy">
                                        <i class="fab fa-instagram fa-lg"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item p-1">
                                    <a href="https://www.youtube.com/DhartAcademy">
                                        <i class="fab fa-youtube fa-lg"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item p-1">
                                    <a href="https://twitter.com/dhartacademy">
                                        <i class="fab fa-twitter fa-lg"></i>
                                    </a>
                                </li>
                            </div>
                        </ul>
                    </div>
                </div>
            </nav>
      `;
    }
}

class Footer extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `	   
        <footer>            
          {*footer code goes here *}         
        </footer>     
      `;
    }
}