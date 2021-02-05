package app;
import static spark.Spark.*;

import services.*;

/*
 * 
INSERT INTO aluno (nome, cpf, senha, is_monitor, curso, id) VALUES ('Rodrigo', '11111111111', '123', 1, 'Ciência da Computação', 1);
INSERT INTO aluno (nome, cpf, senha, is_monitor, curso, id) VALUES ('Marcus', '22222222222', '123', 1, 'Ciência da Computação', 2);
INSERT INTO aluno (nome, cpf, senha, is_monitor, curso, id) VALUES ('Matheus', '33333333333', '123', 1, 'Ciência da Computação', 3);
INSERT INTO aluno (nome, cpf, senha, is_monitor, curso, id) VALUES ('Camilla', '44444444444', '123', 0, 'Ciência da Computação', 4);
INSERT INTO aluno (nome, cpf, senha, is_monitor, curso, id) VALUES ('Diogo', '55555555555', '123', 0, 'Ciência da Computação', 5);


*/

public class Main {
	
	private final static FeedBackService fbService = new FeedBackService();
	private final static AlunoService alService = new AlunoService();
	private final static MonitoriaService monService = new MonitoriaService();
	private final static FavoritosService favService = new FavoritosService();
	
	public static void main(String[]args) {
		
		//CONFIGS INICIAIS DE REQUISIÇÕES E PORTAS
		
		port(getHerokuAssignedPort());
		
		
		/*after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        });*/
		options("/*",	(request, response) -> {

		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//CONEXOES DO FEEDBACK
		
		post("/feedback/add", (request,response) ->  fbService.publicarFeedBack(request));
		
		get("/feedback/getAll", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return fbService.getAllFeedBack();
		});
		
		delete("/feedback/delete", (request, response) -> fbService.deletarFeedBack(request));//funçao para o monitor deletar os feedbacks
		
		//FIM DE CONEXOES DO FEEDBACK
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//CONEXOES DO ALUNO
		
		get("/aluno/getAll", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return alService.getAllAlunoLogs();
		});
		
		//FIM CONEXOES DO ALUNO
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//CONEXOES DA MONITORIA
		
		get("/monitoria/getAll", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return monService.getAllMonitorias();
		});
		
		post("/monitoria/add", (request,response) ->  monService.publicarMonitoria(request, response));
		
		delete("/monitoria/delete", (request, response) -> monService.deletarMonitoria(request));
		
		//FIM CONEXOES DA MONITORIA
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//CONEXOES DOS FAVORITOS
		
		get("/favoritos/getAllAluno", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return favService.getAllFavoritosAluno(request, response);
		});
		
		post("/favoritos/add", (request,response) ->  favService.addFavorito(request));
		
		delete("/favoritos/delete", (request, response) -> favService.deletarFavoritoAluno(request));
		
		//FIM DOS FAVORITOS
	}
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 3214; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
