Programa desenvolvido para rodar em Imagem docker

Comandos docker
1. `docker ps`: Lista os containers em execução.

2. `docker ps -a`: Lista todos os containers, incluindo os parados.

3. `docker logs CONTAINER`: Mostra a saída de um container.

4. `docker logs -f CONTAINER`: Segue a saída de um container.

5. `docker top CONTAINER`: Lista os processos em execução em um container.

6. `docker diff`: Mostra as diferenças com a imagem (arquivos modificados).

7. `docker inspect`: Mostra informações de um container (formatado em json).

8. `docker attach CONTAINER`: Conecta-se a um container.

9. `docker cp CONTAINER:PATH HOSTPATH`: Copia arquivos do container.

10. `docker cp HOSTPATH CONTAINER:PATH`: Copia arquivos para o container.

11. `docker export CONTAINER`: Exporta o conteúdo do container (arquivo tar).

12. `docker exec CONTAINER`: Executa um comando dentro de um container.

13. `docker exec -it CONTAINER /bin/bash`: Abre um shell interativo dentro de um container.

14. `docker wait CONTAINER`: Espera até que o container termine e retorne o código de saída.

15. `docker images`: Lista todas as imagens locais.

16. `docker history IMAGE`: Mostra o histórico da imagem.

17. `docker inspect IMAGE`: Mostra informações (formatado em json).

18. `docker tag IMAGE TAG`: Marca uma imagem.

19. `docker commit CONTAINER IMAGE`: Cria uma imagem (a partir de um container).

20. `docker import URL`: Cria uma imagem (a partir de um tarball).

21. `docker rmi IMAGE`: Deleta imagens.

22. `docker pull REPO:[TAG]`: Puxa uma imagem/repo de um registro.

23. `docker push REPO:[TAG]`: Empurra uma imagem/repo para um registro.

24. `docker search TEXT`: Procura uma imagem no registro oficial.

25. `docker login`: Login em um registro.

26. `docker logout`: Logout de um registro.

27. `docker save REPO:[TAG]`: Exporta uma imagem/repo como um tarball.

28. `docker load`: Carrega imagens de um tarball.

29. `docker volume ls`: Lista todos os volumes.

30. `docker volume create VOLUME`: Cria um volume.

31. `docker volume inspect VOLUME`: Mostra informações (formatado em json).

32. `docker volume rm VOLUME`: Destrói um volume.

33. `docker volume ls --filter="dangling=true"`: Lista todos os volumes pendentes (não referenciados por nenhum container).

34. `docker volume prune`: Deleta todos os volumes (não referenciados por nenhum container).

35. `docker info`: Obtem informações detalhadas sobre o Docker instalado no sistema.

36. `docker images`: Lista todas as imagens do Docker puxadas no sistema com detalhes da imagem como TAG/IMAGE ID/SIZE etc.

37. `docker login`: Login no Docker Hub.

38. `docker stop CONTAINER`: Para um container.

39. `docker start CONTAINER`: Inicia um container.

40. `docker restart CONTAINER`: Reinicia um container.

41. `docker kill CONTAINER`: Mata um container.

42. `docker rm CONTAINER`: Remove um container.

43. `docker run IMAGE`: Executa uma imagem.

44. `docker run -d IMAGE`: Executa uma imagem em modo detached (background).

45. `docker run -p HOSTPORT:CONTAINERPORT IMAGE`: Executa uma imagem mapeando uma porta do host para uma porta do container.

46. `docker run -v HOSTDIR:CONTAINERDIR IMAGE`: Executa uma imagem montando um diretório do host em um diretório do container.

47. `docker run -e "VARNAME=value" IMAGE`: Executa uma imagem definindo uma variável de ambiente.

48. `docker build -t myimage .`: Constroi uma imagem a partir de um Dockerfile no diretório atual.

49. `docker build -t myimage -f /path/to/my/Dockerfile .`: Constroi uma imagem a partir de um Dockerfile específico no diretório atual.

50. `docker build -t myimage -f /path/to/my/Dockerfile .`: Constroi uma imagem a partir de um Dockerfile específico no diretório atual.