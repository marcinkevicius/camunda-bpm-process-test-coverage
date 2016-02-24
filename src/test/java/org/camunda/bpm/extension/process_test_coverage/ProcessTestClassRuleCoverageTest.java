package org.camunda.bpm.extension.process_test_coverage;

import static org.camunda.bpm.extension.process_test_coverage.ProcessTestCoverageProcessConstants.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessDeploymentRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessTestClassRuleCoverageTest {

    // Note, if you assert a coverage on the ClassRule, it means if you run a
    // test without the others, it will probably fail
    @ClassRule
    public static TestCoverageProcessEngineRule classRule = TestCoverageProcessEngineRuleBuilder
        .createClassRule().reportCoverageAfter().assertCoverageAtLeast(1.0).build();

    @Rule // Method rule does the deployment ATM
    public ProcessDeploymentRule deployRule = TestCoverageProcessEngineRuleBuilder.buildDeployRule();

    @Test
    @Deployment(resources = BPMN_PATH)
    public void testPathA() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("path", "A");
        classRule.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    }

    @Test
    @Deployment(resources = BPMN_PATH)
    public void testPathB() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("path", "B");
        classRule.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    }

}
