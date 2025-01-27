<template>
  <header class="header">
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/images/GoNutsLogo.jpg" alt="GoNuts Logo">
      </router-link>
    </div>

    <search-bar class="search-bar" />

    <nav class="nav-links">
      <router-link to="/">Home</router-link>
      <router-link to="../shop">Produkte</router-link>
      <router-link to="../cart">Warenkorb</router-link>
      <router-link v-if="authStore.isAdmin()" to="/admin">Admin</router-link>
    </nav>

    <div class="account-container" ref="accountContainer">
      <router-link v-if="!authStore.isLoggedIn()" to="../login">Anmelden</router-link>

      <div v-else class="profile-icon" @click="toggleAccountMenu">
        <img src="@/assets/images/profile.png" alt="Profil">
      </div>

      <account-menu ref="accountMenu"/>
    </div>
  </header>
</template>

<script setup>
import {ref, onMounted, onUnmounted,} from 'vue';
import SearchBar from "@/components/SearchBar.vue";
import AccountMenu from '../AccountMenu.vue';
import {useAuthStore} from '@/stores/authStore';

const authStore = useAuthStore();

const accountMenu = ref(null);
const accountContainer = ref(null);

const toggleAccountMenu = () => {
  if (accountMenu.value) {
    accountMenu.value.toggleMenu();
  }
};

/**
 * Ruft handleClickOutsideOfMenu auf. Als Referenz wird der AccountContainer übergeben, damit das Account-Icon ebenfalls als "innerhalb" des Menüs wahrgenommen wird.
 */
const callHandleClickOutsideOfMenu = (event) => {
  if (accountMenu.value) {
    accountMenu.value.handleClickOutsideOfMenu(event, accountContainer);
  }
};


onMounted(() => {
  document.addEventListener('click', callHandleClickOutsideOfMenu);
});

onUnmounted(() => {
  document.removeEventListener('click', callHandleClickOutsideOfMenu);
});
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
  position: relative;
  padding-right: 2rem;
}

.nav-links a:not(:last-child)::after {
  content: "|";
  position: absolute;
  right: 0;
  color: #ccc;
}

.account-container a {
  align-items: center;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  background-color: #333;
  color: #fff;
  text-decoration: none;
}

.profile-icon img {
  width: 40px;
  height: 40px;
  cursor: pointer;
}

</style>