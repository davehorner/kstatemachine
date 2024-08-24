
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import io.ktor.http.auth.HttpAuthHeader.Parameters.Realm
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

actual class MainApplication : Application() {

    actual override fun onCreate() {
        super.onCreate()
//        val androidModules = listOf(module {
//            single<EventBroadcaster> { AndroidEventBroadcaster(applicationContext) } //androidContext()) }
//        })
//        initializeKoin(androidModules)
    }

    actual override fun onTerminate() {
        GlobalContext.stopKoin()
        super.onTerminate()
    }
}
