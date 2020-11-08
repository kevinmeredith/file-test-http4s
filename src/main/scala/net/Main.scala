package net

import java.net.InetAddress

import cats.effect.{ExitCode, IO, IOApp, Sync}
import fs2.Stream
import org.http4s.HttpApp
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeServerBuilder

import scala.concurrent.ExecutionContext

object Main extends IOApp {

  private implicit val dsl: Http4sDsl[IO] = Http4sDsl[IO]

  override def run(args: List[String]): IO[ExitCode] = {

    val app: HttpApp[IO] = App.app

    val s: Stream[IO, Unit] = for {
      localHost <- Stream.eval(IO.delay(InetAddress.getLoopbackAddress.getHostAddress))
      _ <-
        BlazeServerBuilder[IO](ExecutionContext.global)
          .bindHttp(8080, localHost)
          .withHttpApp(app)
          .serve
    } yield ()

    s.compile.drain.as(ExitCode.Success)

  }
}
