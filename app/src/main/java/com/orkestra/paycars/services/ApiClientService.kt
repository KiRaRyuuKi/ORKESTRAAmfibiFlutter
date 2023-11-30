import com.google.gson.annotations.SerializedName
import com.orkestra.paycars.controllers.view.ViewDataContent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object ApiClientService {

    private const val BASE_URL = "https://dummyjson.com/"
    private const val STRIPE_URL = "https://api.stripe.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiDataService: ApiDataService = retrofit.create(ApiDataService::class.java)

    fun getApiInterface(): ApiInterfaceService {
        return Retrofit.Builder()
            .baseUrl(STRIPE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterfaceService::class.java)
    }

    data class SignUpRequest(
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String
    )

    data class SignUpResponse(
        @SerializedName("message") val message: String
    )

    interface ApiDataService {

        @POST("signup")
        fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

        @GET("products")
        fun getAllProduct(): Call<ViewDataContent>
    }

    interface ApiInterfaceService {
        // tambahkan endpoint atau fungsi lain sesuai kebutuhan
    }
}
