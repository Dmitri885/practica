package com.example.practica.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.practica.R

<<<<<<< HEAD
=======
/**
 * Класс данных, представляющий товар на главном экране
 * @param id уникальный идентификатор товара
 * @param name название товара
 * @param price цена товара в формате строки (с символом рубля)
 * @param imageRes ресурс изображения товара
 */
>>>>>>> Day-3
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int
)

<<<<<<< HEAD
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    val categories = listOf("Все", "Outdoor", "Tennis")
    var selectedCategory by remember { mutableStateOf("Все") }

=======
/**
 * Главный экран приложения с лентой рекомендаций, категориями и популярными товарами
 *
 * @param navController навигационный контроллер для переходов между экранами
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    // Состояние прокрутки для вертикального скролла
    val scrollState = rememberScrollState()

    // Список доступных категорий для быстрого перехода
    val categories = listOf("Все", "Outdoor", "Tennis")
    // Состояние выбранной категории
    var selectedCategory by remember { mutableStateOf("Все") }

    // Тестовые данные товаров (в реальном приложении будут загружаться из API)
>>>>>>> Day-3
    val products = listOf(
        Product(1, "Nike Air Max", "₽752.00", R.drawable.img_shoe_blue),
        Product(2, "Nike Air Max", "₽752.00", R.drawable.img_shoe_blue)
    )

<<<<<<< HEAD
    Scaffold(
        bottomBar = { BottomBar(navController = navController, currentRoute = "home") },
        containerColor = Color(0xFFF5F7FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.home_title),
=======
    // Scaffold - базовая структура экрана с нижней навигацией
    Scaffold(
        bottomBar = { BottomBar(navController = navController, currentRoute = "home") },
        containerColor = Color(0xFFF5F7FA) // Светло-серый фон
    ) { innerPadding ->
        // Основная колонка с прокруткой
        Column(
            modifier = Modifier
                .padding(innerPadding) // Учитываем отступы от Scaffold
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .verticalScroll(scrollState) // Вертикальная прокрутка
                .padding(horizontal = 16.dp, vertical = 12.dp) // Внутренние отступы
        ) {
            // Заголовок экрана
            Text(
                text = stringResource(id = R.string.home_title), // "Hello, David" из ресурсов
>>>>>>> Day-3
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBox(
                    hint = stringResource(id = R.string.search_hint),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF48B2E7))
                        .clickable { },
=======
            // Строка поиска и кнопка фильтра
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Поле поиска (занимает всё доступное пространство)
                SearchBox(
                    hint = stringResource(id = R.string.search_hint), // "Search" из ресурсов
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))

                // Кнопка фильтрации
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape) // Круглая форма
                        .background(Color(0xFF48B2E7)) // Голубой фон
                        .clickable { /* TODO: открыть экран фильтрации */ },
>>>>>>> Day-3
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

<<<<<<< HEAD
            Text(
                text = stringResource(id = R.string.categories),
=======
            // Заголовок секции категорий
            Text(
                text = stringResource(id = R.string.categories), // "Categories" из ресурсов
>>>>>>> Day-3
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(12.dp))

<<<<<<< HEAD
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
=======
            // Горизонтальный список категорий
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp) // Отступы между элементами
>>>>>>> Day-3
            ) {
                items(categories) { category ->
                    val isSelected = category == selectedCategory
                    CategoryChip(
                        title = category,
                        selected = isSelected,
                        onClick = {
                            selectedCategory = category
<<<<<<< HEAD
                            // переход на каталог с выбранной категорией
=======
                            // Переход на каталог с выбранной категорией
>>>>>>> Day-3
                            navController.navigate("catalog/$category")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
=======
            // Заголовок секции популярных товаров с ссылкой "See all"
>>>>>>> Day-3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
<<<<<<< HEAD
                    text = stringResource(id = R.string.popular),
=======
                    text = stringResource(id = R.string.popular), // "Popular" из ресурсов
>>>>>>> Day-3
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )
                Text(
<<<<<<< HEAD
                    text = stringResource(id = R.string.see_all),
                    fontSize = 14.sp,
                    color = Color(0xFF48B2E7)
=======
                    text = stringResource(id = R.string.see_all), // "See all" из ресурсов
                    fontSize = 14.sp,
                    color = Color(0xFF48B2E7),
                    modifier = Modifier.clickable {
                        // TODO: переход на полный список популярных товаров
                    }
>>>>>>> Day-3
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

<<<<<<< HEAD
=======
            // Горизонтальный список популярных товаров
>>>>>>> Day-3
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products) { product ->
                    ProductCard(product = product)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
=======
            // Заголовок секции промо-акций
>>>>>>> Day-3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
<<<<<<< HEAD
                    text = stringResource(id = R.string.promo),
=======
                    text = stringResource(id = R.string.promo), // "Promo for you" из ресурсов
>>>>>>> Day-3
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )
                Text(
                    text = stringResource(id = R.string.see_all),
                    fontSize = 14.sp,
                    color = Color(0xFF48B2E7)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

<<<<<<< HEAD
            PromoBanner()
=======
            // Промо-баннер
            PromoBanner()

            // Нижний отступ для учета нижней навигации
>>>>>>> Day-3
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

<<<<<<< HEAD
@Composable
private fun SearchBox(hint: String, modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color(0xFFB0B0B0)
=======
/**
 * Компонент поля поиска с иконкой
 * @param hint текст-подсказка в поле ввода
 * @param modifier модификатор для настройки расположения
 */
@Composable
private fun SearchBox(hint: String, modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf(TextFieldValue("")) } // Состояние текста поиска

    OutlinedTextField(
        value = value,
        onValueChange = { value = it }, // Обновление состояния при вводе
        leadingIcon = {
            // Иконка лупы в начале поля
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color(0xFFB0B0B0) // Серый цвет
>>>>>>> Day-3
            )
        },
        placeholder = {
            Text(text = hint, color = Color(0xFFB0B0B0))
        },
<<<<<<< HEAD
        singleLine = true,
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
=======
        singleLine = true, // Однострочный ввод
        modifier = modifier
            .height(48.dp) // Фиксированная высота
            .clip(RoundedCornerShape(16.dp)), // Скругленные углы
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent, // Убираем рамку при фокусе
            unfocusedBorderColor = Color.Transparent, // Убираем рамку без фокуса
            focusedContainerColor = Color.White, // Белый фон при фокусе
            unfocusedContainerColor = Color.White // Белый фон без фокуса
>>>>>>> Day-3
        )
    )
}

<<<<<<< HEAD
=======
/**
 * Компонент чипа категории для выбора
 * @param title название категории
 * @param selected флаг выбранной категории
 * @param onClick колбэк при клике на категорию
 */
>>>>>>> Day-3
@Composable
private fun CategoryChip(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(34.dp)
            .clip(RoundedCornerShape(16.dp))
<<<<<<< HEAD
            .background(if (selected) Color.White else Color(0xFFE8EDF3))
=======
            .background(if (selected) Color.White else Color(0xFFE8EDF3)) // Разный фон для выбранной/невыбранной
>>>>>>> Day-3
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
<<<<<<< HEAD
            color = if (selected) Color(0xFF333333) else Color(0xFF828B99)
=======
            color = if (selected) Color(0xFF333333) else Color(0xFF828B99) // Разный цвет текста
>>>>>>> Day-3
        )
    }
}

<<<<<<< HEAD
=======
/**
 * Карточка товара для отображения в горизонтальном списке
 * @param product данные товара
 */
>>>>>>> Day-3
@Composable
private fun ProductCard(product: Product) {
    Box(
        modifier = Modifier
<<<<<<< HEAD
            .width(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column {
=======
            .width(180.dp) // Фиксированная ширина
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp) // Внутренние отступы
    ) {
        Column {
            // Верхний ряд с иконкой избранного
>>>>>>> Day-3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite_border),
                    contentDescription = "Favorite",
                    tint = Color(0xFFB0B0B0)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

<<<<<<< HEAD
=======
            // Изображение товара
>>>>>>> Day-3
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
<<<<<<< HEAD
                    .height(80.dp)
=======
                    .height(80.dp) // Фиксированная высота
>>>>>>> Day-3
            )

            Spacer(modifier = Modifier.height(8.dp))

<<<<<<< HEAD
=======
            // Бейдж бестселлера (всегда отображается в демо-версии)
>>>>>>> Day-3
            Text(
                text = "BEST SELLER",
                fontSize = 10.sp,
                color = Color(0xFF48B2E7),
                fontWeight = FontWeight.Medium
            )

<<<<<<< HEAD
=======
            // Название товара с обрезкой если не помещается
>>>>>>> Day-3
            Text(
                text = product.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333),
<<<<<<< HEAD
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
=======
                maxLines = 1, // Максимум одна строка
                overflow = TextOverflow.Ellipsis // Многоточие при обрезке
>>>>>>> Day-3
            )

            Spacer(modifier = Modifier.height(4.dp))

<<<<<<< HEAD
=======
            // Нижний ряд с ценой и кнопкой добавления в корзину
>>>>>>> Day-3
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
<<<<<<< HEAD
=======
                // Цена
>>>>>>> Day-3
                Text(
                    text = product.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
<<<<<<< HEAD
=======

                // Кнопка добавления в корзину
>>>>>>> Day-3
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF48B2E7))
<<<<<<< HEAD
                        .clickable { },
=======
                        .clickable { /* TODO: добавить в корзину */ },
>>>>>>> Day-3
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = "Add to cart",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

<<<<<<< HEAD
=======
/**
 * Промо-баннер для отображения акций
 */
>>>>>>> Day-3
@Composable
private fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
<<<<<<< HEAD
            .height(120.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
    ) {
=======
            .height(120.dp) // Фиксированная высота
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
    ) {
        // Фоновое изображение баннера
>>>>>>> Day-3
        Image(
            painter = painterResource(id = R.drawable.img_promo_banner),
            contentDescription = "Promo",
            modifier = Modifier.fillMaxSize()
        )
    }
}

<<<<<<< HEAD
@Composable
fun BottomBar(navController: NavHostController, currentRoute: String) {
    val activeColor = Color(0xFF48B2E7)
    val inactiveColor = Color(0xFFB0B0B0)
=======
/**
 * Компонент нижней навигационной панели
 * Отображает иконки для основных разделов приложения
 *
 * @param navController навигационный контроллер
 * @param currentRoute текущий маршрут для подсветки активной иконки
 */
@Composable
fun BottomBar(navController: NavHostController, currentRoute: String) {
    // Цвета для активной и неактивной иконок
    val activeColor = Color(0xFF48B2E7) // Голубой для активной
    val inactiveColor = Color(0xFFB0B0B0) // Серый для неактивной
>>>>>>> Day-3

    Box(
        modifier = Modifier
            .fillMaxWidth()
<<<<<<< HEAD
            .height(72.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Дом
=======
            .height(72.dp) // Фиксированная высота панели
            .background(Color.White), // Белый фон
        contentAlignment = Alignment.Center
    ) {
        // Ряд с иконками
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), // Отступы по бокам
            horizontalArrangement = Arrangement.SpaceBetween, // Равномерное распределение
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка "Домой"
>>>>>>> Day-3
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                tint = if (currentRoute == "home") activeColor else inactiveColor,
                modifier = Modifier.clickable {
                    if (currentRoute != "home") {
                        navController.navigate("home") {
<<<<<<< HEAD
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
=======
                            popUpTo("home") { inclusive = false } // Очищаем стек до home
                            launchSingleTop = true // Не создаем дубликатов
>>>>>>> Day-3
                        }
                    }
                }
            )

<<<<<<< HEAD

=======
            // Иконка "Избранное"
>>>>>>> Day-3
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Favorites",
                tint = if (currentRoute == "favorite") activeColor else inactiveColor,
                modifier = Modifier.clickable {
                    if (currentRoute != "favorite") {
                        navController.navigate("favorite") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )

<<<<<<< HEAD

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(activeColor),
=======
            // Центральная круглая кнопка для корзины (выделяется)
            Box(
                modifier = Modifier
                    .size(56.dp) // Больше остальных иконок
                    .clip(CircleShape)
                    .background(activeColor), // Всегда голубая
>>>>>>> Day-3
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bag),
                    contentDescription = "Bag",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

<<<<<<< HEAD

            Icon(
                painter = painterResource(id = R.drawable.ic_truck),
                contentDescription = "Orders",
                tint = inactiveColor
            )

            // Профиль
=======
            // Иконка "Заказы"
            Icon(
                painter = painterResource(id = R.drawable.ic_truck),
                contentDescription = "Orders",
                tint = inactiveColor // Пока неактивна всегда
            )

            // Иконка "Профиль"
>>>>>>> Day-3
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile",
                tint = if (currentRoute == "profile") activeColor else inactiveColor,
                modifier = Modifier.clickable {
                    if (currentRoute != "profile") {
                        navController.navigate("profile") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}