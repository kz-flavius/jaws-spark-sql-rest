package apiactors

import akka.actor.ActorRef
import messages.ErrorMessage

import scala.util.{Failure, Success, Try}

/**
 * Created by emaorhian
 */

object ActorsPaths {
  
  def REMOTE_ACTOR_SYSTEM_PREFIX_PATH = "akka.tcp://remoteSystem@"
  
  def LOCAL_SUPERVISOR_ACTOR_NAME = "LocalSupervisor"
  def LOCAL_SUPERVISOR_ACTOR_PATH = s"/user/$LOCAL_SUPERVISOR_ACTOR_NAME"

  def REMOTE_SUPERVISOR_ACTOR_NAME = "RemoteSupervisor"
  def REMOTE_SUPERVISOR_ACTOR_PATH = s"/user/$REMOTE_SUPERVISOR_ACTOR_NAME"

  def GET_QUERIES_ACTOR_NAME = "GetQueries"
  def GET_QUERIES_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_QUERIES_ACTOR_NAME"

  def GET_TABLES_ACTOR_NAME = "GetTables"
  def GET_TABLES_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_TABLES_ACTOR_NAME"

  def RUN_SCRIPT_ACTOR_NAME = "RunScript"
  def RUN_SCRIPT_ACTOR_PATH = s"$REMOTE_SUPERVISOR_ACTOR_PATH/$RUN_SCRIPT_ACTOR_NAME"

  def GET_LOGS_ACTOR_NAME = "GetLogs"
  def GET_LOGS_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_LOGS_ACTOR_NAME"

  def LOGS_WEBSOCKETS_ACTOR_NAME = "LogsWebsockets"
  def LOGS_WEBSOCKETS_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$LOGS_WEBSOCKETS_ACTOR_NAME"

  def GET_RESULTS_ACTOR_NAME = "GetResults"
  def GET_RESULTS_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_RESULTS_ACTOR_NAME"

  def GET_DATABASES_ACTOR_NAME = "GetDatabases"
  def GET_DATABASES_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_DATABASES_ACTOR_NAME"

  def GET_DATASOURCE_SCHEMA_ACTOR_NAME = "GetDatasourceSchemaActor"
  def GET_DATASOURCE_SCHEMA_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_DATASOURCE_SCHEMA_ACTOR_NAME"

  def BALANCER_ACTOR_NAME = "Balancer"
  def BALANCER_ACTOR_PATH = s"$REMOTE_SUPERVISOR_ACTOR_NAME/$BALANCER_ACTOR_NAME"
  
  def DELETE_QUERY_ACTOR_NAME = "DeleteQuery"
  def DELETE_QUERY_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$DELETE_QUERY_ACTOR_NAME"
  
  def REGISTER_PARQUET_TABLE_ACTOR_NAME = "RegisterParquetTable"
  def REGISTER_PARQUET_TABLE_ACTOR_PATH = s"$REMOTE_SUPERVISOR_ACTOR_PATH/$REGISTER_PARQUET_TABLE_ACTOR_NAME"
  
  def GET_PARQUET_TABLES_ACTOR_NAME = "GetParquetTables"
  def GET_PARQUET_TABLES_ACTOR_PATH = s"$LOCAL_SUPERVISOR_ACTOR_PATH/$GET_PARQUET_TABLES_ACTOR_NAME"
}

object ActorOperations {
  def returnResult (tryResult : Try[Any], results : Any, errorMessage : String, senderActor: ActorRef){
    tryResult match {
      case Success(v) => senderActor ! results
      case Failure(e) => senderActor ! ErrorMessage(s"$errorMessage ${e.getMessage}")
    }
  }
}