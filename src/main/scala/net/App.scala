package net

import cats.implicits._
import cats.effect.IO
import fs2.{Stream, text, io}
import org.http4s._
import org.http4s.multipart.{Multipart, Part}

object App {

  def app: HttpApp[IO] = HttpApp.apply[IO] {
    case r: Request[IO] =>
      r
        .as[Multipart[IO]]
        .flatMap { mp: Multipart[IO] =>
          val firstPartWithFile: Vector[(String, Stream[IO, Byte])] =
            mp
              .parts
              .flatMap { p: Part[IO] =>
                p.filename.map { name: String =>
                  (name, p.body)
                }.toVector
            }

          firstPartWithFile
            .headOption match {
            case Some( (fileName, stream) ) =>
              stream
                .through(text.utf8Decode)
                .compile
                .string
                .flatMap { text: String => IO(println("fileName: " + fileName + " with text: " + text) ) }
                .as {
                  Response[IO](status = Status.NoContent)
                }
            case None => IO.pure(Response[IO](status = Status.BadRequest))
          }
        }

  }

}
