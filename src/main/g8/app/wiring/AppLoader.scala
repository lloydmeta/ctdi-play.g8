package wiring

import play.api.ApplicationLoader.Context
import java.util.concurrent.locks.ReentrantReadWriteLock
import play.api._

class AppLoader extends ApplicationLoader {

  // Just to be paranoid
  private[this] val rwl   = new ReentrantReadWriteLock()
  private[this] val rlock = rwl.readLock
  private[this] val wlock = rwl.writeLock

  @SuppressWarnings(Array("org.wartremover.warts.Var", "org.wartremover.warts.Null"))
  private[this] var _maybeComponents: AppComponents = null

  // Returns a handle to the components
  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def components: AppComponents = {
    rlock.lock
    try {
      if (_maybeComponents == null) {
        throw new IllegalStateException("Components not yet set. Has .load been called?")
      } else {
        _maybeComponents
      }
    } finally rlock.unlock
  }

  def load(context: Context) = {
    val appComponents = new AppComponents(context)
    wlock.lock
    try {
      _maybeComponents = appComponents
    } finally wlock.unlock
    appComponents.application
  }

}