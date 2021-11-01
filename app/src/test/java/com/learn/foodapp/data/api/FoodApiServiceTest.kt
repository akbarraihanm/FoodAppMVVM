package com.learn.foodapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodApiServiceTest {
    private lateinit var service: FoodApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getCategories_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("foodresponse.json")
            val body = service.getCategories().body()
            val request = server.takeRequest()
            assertThat(body).isNotNull()
            assertThat(request.path).isEqualTo("/categories.php")
        }
    }

    @Test
    fun getCategory_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("foodresponse.json")
            val body = service.getCategories().body()
            val list = body!!.categories
            val article = list[0]

            assertThat(article.strCategory).isEqualTo("Beef")
            assertThat(article.strCategoryThumb).isEqualTo("https://www.themealdb.com/images/category/beef.png")
            assertThat(article.idCategory).isEqualTo("1")
        }
    }
}