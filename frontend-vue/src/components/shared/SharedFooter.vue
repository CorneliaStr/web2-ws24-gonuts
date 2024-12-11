<template>
    <footer class="footer">
        <div class="footer-container">
            <!-- Logo und Adresse -->
            <div class="footer-column">
                <img src="@/assets/images/GoNutsLogo.jpg" alt="GoNuts Logo" class="footer-logo">
                <address>
                    Emil-Figge-Straße 42<br>
                    44227 Dortmund<br>
                    <span>&copy; GoNuts!</span>
                </address>
            </div>

            <!-- Hauptmenü -->
            <div class="footer-column">
                <h4>HAUPTMENÜ</h4>
                <ul class="footer-menu">
                    <li><a href="">Home</a></li>
                    <li><a href="../shop">Produkte</a></li>
                    <li><a href="../favorites?id=1">Merkliste</a></li>
                </ul>
            </div>

            <!-- Social Media Links -->
            <div class="footer-column">
                <h4>FIND US ON</h4>
                <ul class="footer-social">
                    <li><a href="#">Facebook</a></li>
                    <li><a href="#">X / Twitter</a></li>
                    <li><a href="#">Instagram</a></li>
                </ul>
            </div>
        </div>
    </footer>
</template>

<script>

</script>

<style>
/* Footer Styling */
.footer {
  background-color: #f9f9f9;
  padding: 20px 0;
  border-top: 1px solid #e0e0e0;
  font-size: 14px;
  width: 100%;
}

.footer-container {
  max-width: 900px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
}

.footer-column {
  flex: 1;
  padding: 0 20px;
}

.footer-logo {
  width: 60px;
  height: 60px;
  margin-bottom: 10px;
}

.footer-column address {
  font-style: normal;
  color: #333;
  margin-top: 10px;
}

.footer-column h4 {
  font-size: 16px;
  margin-bottom: 10px;
  color: #555;
}

.footer-menu,
.footer-social {
  list-style: none;
  padding: 0;
}

.footer-menu li,
.footer-social li {
  margin-bottom: 5px;
}

.footer-menu li a,
.footer-social li a {
  text-decoration: none;
  color: #333;
}

.footer-menu li a:hover,
.footer-social li a:hover {
  color: #555;
  text-decoration: underline;
}

/* Copyright */
.footer-column span {
  display: block;
  margin-top: 10px;
  color: #999;
}
</style>