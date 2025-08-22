# Schedules

Projeto para gestão de horários de atendimentos médicos com base no teste de admissão do processo de seleção da Unvoid. O objetivo deste repositório é exercitar a manipulação de datas em Java e aplicação da metodologia TDD.

---

## 📖 Visão Geral

### ✨ Funcionalidades
- **Validação de disponibilidade de horário**: Os médicos têm uma `CalendarAvailability`, que define quando eles estão disponíveis semanalmente (por exemplo, de segunda a sexta, das 9h às 17h). Para agendar uma consulta, os pacientes devem selecionar um `CalendarSlot` (por exemplo, 15 de janeiro de 2024, segunda-feira, às 15h).
- **Validar Disponibilidade com Eventos Existentes**: Quando um paciente agenda uma consulta, isso cria um `CalendarEvent`. Um médico não pode estar em dois lugares ao mesmo tempo, então `CalendarEvent` deve bloquear a disponibilidade do médico. Isso é semelhante à Validação de disponibilidade de horário, mas agora é necessário lidar com eventos do calendário.
- **Validar Disponibilidade com Buffer de Eventos**: Cada `CalendarEvent` pode, opcionalmente, ter um buffer, que é reservado um bloco de tempo antes e/ou depois do evento, no qual nenhum outro evento pode ser agendado. Por exemplo: a reunião é das 16h às 17h, mas vamos também bloquear 30 minutos antes para preparação e 1 hora depois, caso a reunião demore mais do que o esperado.
- **Gerar Slots Disponíveis de 30 Minutos**: Encontra os horários de 30 minutos disponíveis no calendário de um médico em um período específico.
- **Gerar Slots Disponíveis para Múltiplos Médicos**: Encontrar os horários em que TODOS os médicos estão disponíveis em um período específico. Semelhante a Gerar Horários Disponíveis de 30 Minutos, mas agora é necessário lidar com vários médicos.

## 📞 Contato

Se você tiver dúvidas ou sugestões, entre em contato:
- **Email**: jorderpgomes@gmail.com
- **LinkedIn**: [Jorder Gomes](https://www.linkedin.com/in/jorder-gomes-645636186/)
