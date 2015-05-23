package lib

case class ExampleService(
  organizationKey: String,
  applicationKey: String,
  version: String = "latest"
) {

  val label = s"$organizationKey/$applicationKey"

  val docsUrl = if (version == "latest") {
    s"/$organizationKey/$applicationKey"
  } else {
    s"/$organizationKey/$applicationKey/$version"
  }

  val originalJsonUrl = s"/$organizationKey/$applicationKey/$version/original"
  val serviceJsonUrl = s"/$organizationKey/$applicationKey/$version/service.json"

}

object Util {

  val Host = Config.requiredString("apidoc.www.host")

  val SubscriptionsText = "Subscriptions"
  val SubscriptionsVersionsCreateText = "For applications that I watch, email me when a version is created."

  val AddApplicationText = "Add Application"
  val OrgDetailsText = "Org Details"
  val ServiceJsonText = "service.json"

  val ApidocExample = ExampleService("gilt", "apidoc-api")
  val ApidocExampleWithVersionNumber = ExampleService("gilt", "apidoc-api", Config.requiredString("git.version"))
  val ApidocGeneratorExample = ExampleService("gilt", "apidoc-generator")
  val ApidocSpecExample = ExampleService("gilt", "apidoc-spec")
  val Examples = Seq(ApidocExample, ApidocGeneratorExample, ApidocSpecExample)

  val ApidocCliGitHubUrl = "https://github.com/gilt/apidoc-cli"
  val GitHubUrl = "https://github.com/gilt/apidoc"
  val GeneratorGitHubUrl = "https://github.com/gilt/apidoc-generator"

  def fullUrl(stub: String): String = s"$Host$stub"

  def formatUri(value: String): String = {
    if (value.toLowerCase.trim.startsWith("http")) {
      value
    } else {
      "http://" + value
    }
  }

  def defaultQuery(query: Option[String], orgKey: Option[String]): String = {
    query match {
      case Some(q) => q
      case None => {
        orgKey match {
          case Some(key) => s"org:$key"
          case None => ""
        }
      }
    }
  }


}
