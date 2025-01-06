<template>
  <div v-if="menuVisible" class="menu">
    <ul>
      <li @click="closeMenu">Profil</li>
      <li @click="closeMenu">Bestellungen</li>
      <li @click="navigateToFavorites">Favoriten</li>
      <li @click="logout">Abmelden</li>
    </ul>
  </div>
</template>

<script setup>
import {ref, defineExpose} from 'vue';
import router from "@/router/router.js";
import {useAuthStore} from '@/stores/authStore';

const authStore = useAuthStore();

const menuVisible = ref(false);

const toggleMenu = () => {
  menuVisible.value = !menuVisible.value;
};

const closeMenu = () => {
  menuVisible.value = false;
};

const navigateToFavorites = () =>  {
  closeMenu();
  router.push('/favorites/1')
};

const logout = () => {
  authStore.logout();
  closeMenu();
};

/**
 * Wenn außerhalb des Menüs geklickt wird, wird das Menü geschlossen.
 * @param menuRef Referenz zu dem Element, das das Menü umschließt.
 */
const handleClickOutsideOfMenu = (event, menuRef) => {
  if (menuRef.value && !menuRef.value.contains(event.target)) {
    closeMenu();
  }
};

defineExpose({
  toggleMenu, handleClickOutsideOfMenu
});
</script>

<style scoped>
.menu {
  position: absolute;
  top: 70px;
  right: 10px;
  background-color: white;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  border: 1px solid #ccc;
  z-index: 10;
  width: 200px;
}

.menu ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.menu li {
  padding: 12px 16px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.menu li:hover {
  background-color: #f0f0f0;
}
</style>
