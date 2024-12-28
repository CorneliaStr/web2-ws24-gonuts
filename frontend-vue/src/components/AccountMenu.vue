<template>
  <div class="account-menu">
    <div v-if="menuVisible" class="menu">
      <ul>
        <li @click="navigateTo('profile')">Profil</li>
        <li @click="navigateTo('orders')">Bestellungen</li>
        <li @click="navigateTo('settings')">Einstellungen</li>
        <li @click="logout">Abmelden</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, defineExpose} from 'vue';

const menuVisible = ref(false);

const toggleMenu = () => {
  menuVisible.value = !menuVisible.value;
};

const closeMenu = () => {
  menuVisible.value = false;
};

const navigateTo = (page) => {
  closeMenu()
  console.log(`Navigiere zu: ${page}`);
};

const logout = () => {
  closeMenu()
  console.log('Abgemeldet');
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
  top: 50px;
  right: 0;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
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
