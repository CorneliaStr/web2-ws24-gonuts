<template>
  <header class="header">
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/images/GoNutsLogo.jpg" alt="GoNuts Logo">
      </router-link>
    </div>
    <div class="search-bar">
      <input type="text" placeholder="Suche...">
    </div>
    <nav class="nav-links">
      <router-link to="/">Home</router-link>
      <router-link to="../shop">Produkte</router-link>
      <router-link to="../favorites/1">Merkliste</router-link>
      <router-link to="../cart">Warenkorb</router-link>
    </nav>
    <button class="cart-btn" onclick="location.href='../cart?id=1'">
      <span class="cart-icon">🛒</span> Warenkorb
    </button>
    <div class="profile-icon">
      <router-link to="/account">
        <img src="@/assets/images/profile.png" alt="Profil">
      </router-link>
    </div>
  </header>
</template>

<script setup>

</script>

<style>

header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px;
  background-color: #ffffff;
  border-bottom: 1px solid #ddd;
  width: 100%;
  position: sticky;
  top: 0;
  z-index: 100;
}

.logo img {
  width: 50px;
  height: 50px;
}

.search-bar {
  flex-grow: 1;
  margin: 0 20px;
}

.search-bar input {
  width: 100%;
  padding: 8px 10px;
  border-radius: 20px;
  border: 1px solid #ccc;
  outline: none;
}

.nav-links {
  display: flex;
  gap: 2rem;
}

.nav-links a {
  text-decoration: none;
  color: #333;
  font-size: 16px;
  font-weight: 500;
}

.nav-links a {
  position: relative;
  padding-right: 2rem;
}

.nav-links a:not(:last-child)::after {
  content: "|";
  position: absolute;
  right: 0;
  color: #ccc;
}

.cart-btn {
  display: flex;
  gap: 5px;
  margin-right: 1rem;
}

.cart-icon {
  font-size: 18px;
}

.profile-icon img {
  width: 40px;
  height: 40px;
}

</style>